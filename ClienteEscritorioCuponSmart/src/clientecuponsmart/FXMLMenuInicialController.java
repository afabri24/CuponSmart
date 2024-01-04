package clientecuponsmart;

import clientecuponsmart.modelo.pojo.Usuarios;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class FXMLMenuInicialController implements Initializable {

    Usuarios usuarioSesion;
    
    private boolean esAdminGeneral=false;
    
    @FXML
    private Label lbEmpresa;
    @FXML
    private Label lbUsuario;
    @FXML
    private Button btnCambiante;
    @FXML
    private Button btnUsuarios;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAbrirPromociones(ActionEvent event) {
    }

    @FXML
    private void btnAbrirEmpresas(ActionEvent event) {
        if(esAdminGeneral==true){
            abrirAdminEmpresas();
        }else{
            //TODOabirsucursales
        }
        
    }

    @FXML
    private void btnAbrirCupones(ActionEvent event) {
    }
    
    public void inicializarInformacionUsuario(Usuarios usuario){
        this.usuarioSesion=usuario;
        if (usuarioSesion==null){
            System.out.println("usuario nulo");
        }
        lbUsuario.setText("Nombre: "+usuarioSesion.getNombre()+" "+usuarioSesion.getApellidoPaterno()+" "+usuario.getApellidoMaterno());
        
        if("general".equals(usuarioSesion.getRol())){
           lbEmpresa.setText("Cupon Smart, administrador General");
           System.out.println(usuarioSesion.getRol());
           btnUsuarios.setDisable(false);
           esAdminGeneral=true;
        }else{
            lbEmpresa.setText("empresaTODO, administrador empresa");
            btnCambiante.setText("Sucursales");
        }
        
    }
    
    
    public void abrirAdminEmpresas(){
        try{
            FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLEmpresas.fxml"));
            Parent vista = loadVista.load();
            
            
           FXMLEmpresasController controller =loadVista.getController();
           controller.inicializarInformacion();
           
           Scene escenaAdmin= new Scene(vista);
           Stage escenarioAdmin=new Stage();
           escenarioAdmin.setScene(escenaAdmin);
           escenarioAdmin.setTitle("Empresas");
           escenarioAdmin.initModality(Modality.APPLICATION_MODAL);
           escenarioAdmin.showAndWait();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
   
    public void abrirAdminSucursales(){
        try{
            FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLSucursales.fxml"));
            Parent vista = loadVista.load();
            
            
           //FXMLEmpresasController controller =loadVista.getController();
           //controller.inicializarInformacion();
           
           Scene escenaAdmin= new Scene(vista);
           Stage escenarioAdmin=new Stage();
           escenarioAdmin.setScene(escenaAdmin);
           escenarioAdmin.setTitle("Sucursales");
           escenarioAdmin.initModality(Modality.APPLICATION_MODAL);
           escenarioAdmin.showAndWait();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAbirUsuarios(ActionEvent event) {
    }
}
