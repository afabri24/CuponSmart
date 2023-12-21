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
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class FXMLMenuInicialController implements Initializable {

    Usuarios usuarioSesion;
    
    @FXML
    private Label lbEmpresa;
    @FXML
    private Label lbUsuario;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAbrirPromociones(ActionEvent event) {
    }

    @FXML
    private void btnAbrirEmpresas(ActionEvent event) {
        try{
            FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLEmpresas.fxml"));
            Parent vista = loadVista.load();
            
            
           //FXMLAdminPacientesController controller =loadVista.getController();
           //controller.inicializarInformacion(medicoSesion.getIdMedico());
           
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

    @FXML
    private void btnAbrirCupones(ActionEvent event) {
    }
    
    public void inicializarInformacionUsuario(Usuarios usuario){
        this.usuarioSesion=usuario;
        if (usuarioSesion==null){
            System.out.println("usuario nulo");
        }
        lbUsuario.setText("Nombre: "+usuarioSesion.getNombre()+" "+usuarioSesion.getApellidoPaterno()+" "+usuario.getApellidoMaterno());
        
        if(usuarioSesion.getIdEmpresa()==0){
            lbEmpresa.setText("Cupon Smart, administrador General");
        }else{
            lbEmpresa.setText("empresaTODO, administrador empresa");
        }
        
    }
}
