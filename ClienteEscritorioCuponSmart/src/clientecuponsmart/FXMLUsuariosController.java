/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientecuponsmart;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author afabri24
 */
public class FXMLUsuariosController implements Initializable {

    @FXML
    private TableView<?> tvUsuarios;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colApellidoPaterno;
    @FXML
    private TableColumn<?, ?> colCorreoElectronico;
    @FXML
    private TableColumn<?, ?> colUsername;
    @FXML
    private TableColumn<?, ?> colRol;
    @FXML
    private TextField tfBuscarUsuarios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRegistrarUsuario(ActionEvent event) {
    }

    @FXML
    private void btnEditarUsuario(ActionEvent event) {
    }

    @FXML
    private void btnEliminarUsuario(ActionEvent event) {
    }
    
}
