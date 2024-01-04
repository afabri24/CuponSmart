
package clientecuponsmart;

import clientecuponsmart.interfaz.IRespuesta;
import clientecuponsmart.modelo.dao.EmpresasDAO;
import clientecuponsmart.modelo.pojo.Empresas;
import clientecuponsmart.modelo.pojo.Mensaje;
import clientecuponsmart.utils.Utilidades;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author afabri24
 */
public class FXMLFormularioEmpresasController implements Initializable {
    private Empresas empresaModificar;
    private String estado;
    private IRespuesta observador;
    
    private int bandEditar=0;
    
    private File logoSeleccionado;
    
    private Empresas empresa;

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfNombreComercial;
    @FXML
    private TextField tfRepresentante;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfWeb;
    @FXML
    private TextField tfRFC;
    @FXML
    private RadioButton rbInactiva;
    @FXML
    private Label lbErrorEmpresa;
    @FXML
    private Label lbErrorComercial;
    private Label lbInformacionDireccion;
    @FXML
    private Label lbErrorEmail;
    @FXML
    private Label lbErrorNombreRepresentante;
    @FXML
    private Label lbErrorRFC;
    @FXML
    private Label lbErrorTelefono;
    @FXML
    private ToggleGroup tgEstatus;
    @FXML
    private RadioButton rbActiva;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnCL;
    @FXML
    private Button btnGL;
    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfNumero;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private TextField tfCiudad;
    @FXML
    private Label lbErrorCalle;
    @FXML
    private Label lbErrorNumero;
    @FXML
    private Label lbErrorCodigoPostal;
    @FXML
    private Label lbErrorCiudad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tgEstatus.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
               if(rbActiva.isSelected()){
                   estado="activo";
               }else{
                   estado="inactivo";
               }
            }
            
        });
    }    


    
    public void inicializarInformacionEmpresa(Empresas empresaModificar, IRespuesta observador){
        this.empresaModificar=empresaModificar;
        this.observador=observador;
        if(empresaModificar!=null){
            bandEditar=1;
            tfNombre.setText(empresaModificar.getNombre());
            tfNombreComercial.setText(empresaModificar.getNombreComercial());
            tfRepresentante.setText(empresaModificar.getRepresentanteLegal());
            tfEmail.setText(empresaModificar.getEmail());
            tfTelefono.setText(empresaModificar.getTelefono());
            tfCalle.setText(empresaModificar.getDireccion());
            tfCodigoPostal.setText(empresaModificar.getCodigoPostal());
            tfCiudad.setText(empresaModificar.getCiudad());
            tfWeb.setText(empresaModificar.getPaginaWeb());
            tfRFC.setText(empresaModificar.getRFC());
            
            tfEmail.setDisable(true);
            btnCL.setDisable(false);
            btnGL.setDisable(false);
            
            obtenerLogoServicio();    
            
            if("activo".equals(empresaModificar.getEstatus())){
                rbActiva.setSelected(true);
            }
            if("inactivo".equals(empresaModificar.getEstatus())){
                rbInactiva.setSelected(true);
            }
        }
    }

    @FXML
    private void btnCargarLogo(ActionEvent event) {
        FileChooser dialogoSeleccionImg =new FileChooser();
        dialogoSeleccionImg.setTitle("Seleciona una imagen");
        
        FileChooser.ExtensionFilter filtroImg = new FileChooser.ExtensionFilter("Archivos PNG(*.png)", "*.png","Archivos JPEG(*.jpeg)", "*.jpeg","Archivos JPG(*.jpg)", "*.jpg");
        dialogoSeleccionImg.getExtensionFilters().add(filtroImg);
        
        Stage stageActual= (Stage) tfEmail.getScene().getWindow();
        logoSeleccionado= dialogoSeleccionImg.showOpenDialog(stageActual);
        
        if(logoSeleccionado!=null){
            mostrarLogo(logoSeleccionado);
        }
        
    }

    @FXML
    private void btnGuardarLogo(ActionEvent event) {
        if(logoSeleccionado != null){
            subirLogoServidor(logoSeleccionado);
        }else{
             Utilidades.mostrarAlertaSimple("Selecciona Imagen", "Para subir una fotografia del paciente debes selecionarla", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void btnGuardarEmpresa(ActionEvent event) {
        Empresas empresaNueva=new Empresas();
        
        if(comprobarVacios()){
            empresaNueva.setNombre(tfNombre.getText());
            empresaNueva.setNombreComercial(tfNombreComercial.getText());
            empresaNueva.setRepresentanteLegal(tfRepresentante.getText());
            empresaNueva.setEmail(tfEmail.getText());
            empresaNueva.setTelefono(tfTelefono.getText());
            empresaNueva.setDireccion(tfCalle.getText());
            empresaNueva.setCodigoPostal(tfCodigoPostal.getText());
            empresaNueva.setCiudad(tfCiudad.getText());
            empresaNueva.setPaginaWeb(tfWeb.getText());
            empresaNueva.setRFC(tfRFC.getText());

            if(rbActiva.isSelected()){
                empresaNueva.setEstatus("activo");
            }
            if(rbInactiva.isSelected()){
                empresaNueva.setEstatus("inactivo");
            }
            if(bandEditar==0){
                guardarEmpresa(empresaNueva);
            }
            if(bandEditar==1){
               empresaNueva.setIdEmpresa(empresaModificar.getIdEmpresa());
               editarEmpresa(empresaNueva);
            }
        }
        
    }
    
    public boolean comprobarVacios(){
        String nombre=tfNombre.getText();
        String nombreComercial=tfNombreComercial.getText();
        String representanteLegal=tfRepresentante.getText();
        String email=tfEmail.getText();
        String direccion=tfCalle.getText();
        String codigoPostal=tfCodigoPostal.getText();
        String ciudad=tfCiudad.getText();
        String telefono=tfTelefono.getText();
        String paginaWeb=tfWeb.getText();
        String RFC=tfRFC.getText();
        
        
        if(nombre.isEmpty()){
            lbErrorEmpresa.setText("Nombre empresa requerido");
            return false;
        }
        if(nombreComercial.isEmpty()){
            lbErrorComercial.setText("Nombre comercial requerido");
            return false;
        }
        if(representanteLegal.isEmpty()){
            lbErrorNombreRepresentante.setText("Nombre representante requerido");
            return false;
        }
        if(email.isEmpty()){
            lbErrorEmail.setText("Correo electrónico requerido");
            return false;
        }
        if(telefono.isEmpty()){
            lbErrorTelefono.setText("Teléfono requerido");
            return false;
        }
        if(direccion.isEmpty()){
            lbErrorCalle.setText("Direccion requerida");
            return false;
        }
        if(codigoPostal.isEmpty()){
            lbErrorCodigoPostal.setText("Codigo Postal requerido");
            return false;
        }
        if(ciudad.isEmpty()){
            lbErrorCiudad.setText("Ciudad requerido");
            return false;
        }
        if(paginaWeb.isEmpty()){
            lbInformacionDireccion.setText("Página web requerida");
            return false;
        }
        if(RFC.isEmpty()){
            lbErrorRFC.setText("RFC requerido");
            return false;
        }
        
        return true;
    }
      
    
    private void mostrarLogo(File img){
        try{
            BufferedImage buffer =ImageIO.read(img);
            Image image= SwingFXUtils.toFXImage(buffer, null);
            imgLogo.setImage(image);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void subirLogoServidor(File imagen){
        try{
           byte[] imgBytes = Files.readAllBytes(imagen.toPath());
           Mensaje msj=EmpresasDAO.subirLogoEmpresa(empresaModificar.getIdEmpresa(), imgBytes);
           if(!msj.isError()){
               Utilidades.mostrarAlertaSimple("Se guardo la imagen correctamente", msj.getMensaje(), Alert.AlertType.INFORMATION);
           }else{
               Utilidades.mostrarAlertaSimple("error", msj.getMensaje(), Alert.AlertType.ERROR);
           }
       }catch(IOException e){
           Utilidades.mostrarAlertaSimple("error", "No se ha podido cargar tu logo", Alert.AlertType.ERROR);
       }
 
    }
    
    private void obtenerLogoServicio(){
        Empresas empresaLogo=EmpresasDAO.obtenerLogoEmpresa(empresaModificar.getIdEmpresa());
        
        if(empresaModificar!=null&& empresaLogo.getLogoBase64()!=null && !empresaLogo.getLogoBase64().isEmpty()){
            mostrarLogoServidor(empresaLogo.getLogoBase64());
        }
    
    }
    private void mostrarLogoServidor(String imgBase64){
        byte[] foto = Base64.getDecoder().decode(imgBase64.replaceAll("\\n", ""));
        Image image =new Image(new ByteArrayInputStream(foto));
        imgLogo.setImage(image);
    }

    private void guardarEmpresa(Empresas empresaNueva) {
        Mensaje msj=EmpresasDAO.registrarEmpresa(empresaNueva);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Empresa registrada", msj.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarGuardadoEmpresa(empresa.getNombre());
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al registrar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void editarEmpresa(Empresas empresaNueva) {
        Mensaje msj=EmpresasDAO.editarEmpresa(empresaNueva);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Empresa editada", msj.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarGuardadoEmpresa(empresaModificar.getNombre());
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al editar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void cerrarVentana(){
        Stage escenario = (Stage) tfNombre.getScene().getWindow();
        escenario.close();
    }
    
    
}
