package clientecuponsmart;

import clientecuponsmart.modelo.pojo.Usuarios;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


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
