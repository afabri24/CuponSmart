
package clientecuponsmart;

import static clientecuponsmart.modelo.dao.InicioSesionDAO.IniciarSesion;
import clientecuponsmart.modelo.pojo.RespuestaLogin;
import clientecuponsmart.modelo.pojo.Usuarios;
import clientecuponsmart.utils.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author afabri24
 */
public class FXMLIniciarSesionController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private Label lbErrorUsername;
    @FXML
    private Label lbErrorContrasenia;
    @FXML
    private PasswordField pfContrasenia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnIniciarSesion(ActionEvent event) {
        String username=tfUsername.getText();
        String contrasenia=pfContrasenia.getText();
        lbErrorUsername.setText("");
        lbErrorContrasenia.setText("");
        boolean isValido = true;
        if(username.isEmpty()){
            lbErrorUsername.setText("El numero personal es obligatrio");
            isValido=false;
        }if(contrasenia.isEmpty()){
            lbErrorContrasenia.setText("La contraseña es obligatria");
            isValido=false;
        }
        if(isValido){
            verificarCredenciales(username,contrasenia);
            
        }
    }

    private void verificarCredenciales(String username,String contrasenia){
        RespuestaLogin respuesta=IniciarSesion(username,contrasenia);
        if(respuesta.getError()==false){
            Utilidades.mostrarAlertaSimple("Credenciales Correctas",
                       respuesta.getMensaje(),Alert.AlertType.INFORMATION);
            irPantallaPrincipal(respuesta.getUsuarioSesion());
        }else{
             Utilidades.mostrarAlertaSimple("Credenciales Incorrectas",
                       respuesta.getMensaje(),Alert.AlertType.ERROR);
        }
    }
    private void irPantallaPrincipal(Usuarios usuario){
        Stage stageActual=(Stage) tfUsername.getScene().getWindow();
        try {
            FXMLLoader loadMain= new FXMLLoader(getClass().getResource("FXMLMenuInicial.fxml"));
            Parent vista = loadMain.load();
            
            FXMLMenuInicialController controladorMenuInicial = loadMain.getController();
            //controladorMenuInicial.inicializarInformacionMedico(usuario);
            
            Scene escena= new Scene(vista);
            stageActual.setScene(escena);
            stageActual.setTitle("Home");
            stageActual.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
