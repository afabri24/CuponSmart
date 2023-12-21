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
public class FXMLEmpresasController implements Initializable {

    @FXML
    private TableView<?> tvEmpresas;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colRepresentante;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colEstatus;
    @FXML
    private TextField tfBuscarEmpresas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRegistrarEmpresa(ActionEvent event) {
    }

    @FXML
    private void btnEditarEmpresa(ActionEvent event) {
    }

    @FXML
    private void btnEliminarEmpresa(ActionEvent event) {
    }
    
}
