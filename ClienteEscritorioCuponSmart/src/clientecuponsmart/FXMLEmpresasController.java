
package clientecuponsmart;

import clientecuponsmart.interfaz.IRespuesta;
import clientecuponsmart.modelo.dao.EmpresasDAO;
import clientecuponsmart.modelo.pojo.Empresas;
import clientecuponsmart.modelo.pojo.Mensaje;
import clientecuponsmart.utils.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author afabri24
 */
public class FXMLEmpresasController implements Initializable, IRespuesta {

    private ObservableList<Empresas> empresas;
    
    
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colRepresentante;
    @FXML
    private TableColumn colEmail;
    
    @FXML
    private TableView<Empresas> tvEmpresas;
    @FXML
    private TextField tfBuscarEmpresas;
    @FXML
    private TableColumn colPaginaWeb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empresas = FXCollections.observableArrayList();
        configurarTabla();
    }    

    @FXML
    private void btnRegistrarEmpresa(ActionEvent event) {
        irFormulario(null);
    }

    @FXML
    private void btnEditarEmpresa(ActionEvent event) {
        int posicion=tvEmpresas.getSelectionModel().getSelectedIndex();
        if(posicion!=-1){
            Empresas empresa=empresas.get(posicion);
            irFormulario(empresa);
        }else{
            Utilidades.mostrarAlertaSimple("Seleccion Requerida", "Debes seleccionar una empresa", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void btnEliminarEmpresa(ActionEvent event) {
       Empresas empresaSeleccion= tvEmpresas.getSelectionModel().getSelectedItem();
       if(empresaSeleccion != null){
           Optional<ButtonType> respuesta=Utilidades.mostrarAlertaConfrimacion("Confirmar eliminacion", "¿Estas seguro de eliminar la empresa "+empresaSeleccion.getNombre()+"?");
           if(respuesta.get()==ButtonType.OK){
               eliminarEmpresa(empresaSeleccion);
           }
       }else{
           Utilidades.mostrarAlertaSimple("Seleccion Requerida", "Debes seleccionar una empresa", Alert.AlertType.WARNING);
       }
    }

    private void configurarTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colRepresentante.setCellValueFactory(new PropertyValueFactory<>("representanteLegal"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPaginaWeb.setCellValueFactory(new PropertyValueFactory<>("paginaWeb"));
    }
    
    public void inicializarInformacion(){
        descargarEmpresas();
        inicializarBusquedaEmpresa();
    }
    
    
    private void descargarEmpresas(){
        HashMap<String, Object> respuesta = EmpresasDAO.obtenerEmpresas();
        if(!(boolean)respuesta.get("Error")){
            List<Empresas> listaWS =(List<Empresas>) respuesta.get("empresas");
            empresas.addAll(listaWS);
            tvEmpresas.setItems(empresas);
        }else{
            Utilidades.mostrarAlertaSimple("Error", (String) respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
    }
    
    public void irFormulario(Empresas empresa){
        try{
               FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLFormularioEmpresas.fxml"));
               Parent vista = loadVista.load();

               FXMLFormularioEmpresasController controller=loadVista.getController();
               controller.inicializarInformacionEmpresa(empresa,this);
                         
               Scene escenaAdmin= new Scene(vista);
               Stage escenarioAdmin=new Stage();
               escenarioAdmin.setScene(escenaAdmin);
               escenarioAdmin.setTitle("Empresa");
               escenarioAdmin.initModality(Modality.APPLICATION_MODAL);
               escenarioAdmin.showAndWait();
           }catch(IOException e){
               e.printStackTrace();
           }
    }
    
    @Override
    public void notificarGuardadoEmpresa(String nombreEmpresa) {
        System.out.println("Nombre Empresa: "+nombreEmpresa);
        empresas.clear();
        descargarEmpresas();
    }
    
    private void eliminarEmpresa(Empresas empresaEliminar){
        Mensaje msj =EmpresasDAO.eliminarEmpresa(empresaEliminar);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Empresa Borrada", msj.getMensaje(), Alert.AlertType.INFORMATION);
            empresas.clear();
            descargarEmpresas();
        }else{
            Utilidades.mostrarAlertaSimple("Error al Borrar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void inicializarBusquedaEmpresa(){
        if (empresas !=null){
            FilteredList<Empresas> filtroEmpresa=new FilteredList<>(empresas,p -> true);
            tfBuscarEmpresas.textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    filtroEmpresa.setPredicate(busqueda->{
                        //Regla cuando es vacio
                        if(newValue==null|| newValue.isEmpty()){
                            return true;
                        }
                        String lowerFilter= newValue.toLowerCase();
                        //Reglas de  filtrado
                        if(busqueda.getNombre().toLowerCase().contains(lowerFilter)){
                            return true;
                        }
                        if(busqueda.getNombreComercial().toLowerCase().contains(lowerFilter)){
                            return true;
                        }
                        if(busqueda.getTelefono().toLowerCase().contains(lowerFilter)){
                         return true;   
                        }
                        return false;
                    });
                }
            
            });
            SortedList<Empresas> empresasOrdenadas = new SortedList<>(filtroEmpresa);
            empresasOrdenadas.comparatorProperty().bind(tvEmpresas.comparatorProperty());
            tvEmpresas.setItems(empresasOrdenadas);
        }
    }

}
