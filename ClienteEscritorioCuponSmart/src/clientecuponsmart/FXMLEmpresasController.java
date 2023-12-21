/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientecuponsmart;

import clientecuponsmart.modelo.dao.EmpresasDAO;
import clientecuponsmart.modelo.pojo.Empresas;
import clientecuponsmart.utils.Utilidades;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author afabri24
 */
public class FXMLEmpresasController implements Initializable {

    private ObservableList<Empresas> empresas;
    
    
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colRepresentante;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colEstatus;
    
    @FXML
    private TableView<Empresas> tvEmpresas;
    @FXML
    private TextField tfBuscarEmpresas;

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
    }

    @FXML
    private void btnEditarEmpresa(ActionEvent event) {
    }

    @FXML
    private void btnEliminarEmpresa(ActionEvent event) {
    }

    private void configurarTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colRepresentante.setCellValueFactory(new PropertyValueFactory<>("representanteLegal"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEstatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
    }
    
    public void inicializarInformacion(){
        descargarEmpresas();
        //inicializarBusquedaPaciente();
    }
    
    
    private void descargarEmpresas(){
        HashMap<String, Object> respuesta = EmpresasDAO.obtenerEmpresas();
        if(!(boolean) respuesta.get("Error")){
            List<Empresas> listaWS =(List<Empresas>) respuesta.get("empresas");
            empresas.addAll(listaWS);
            tvEmpresas.setItems(empresas);
        }else{
            Utilidades.mostrarAlertaSimple("Error", (String) respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
    }
}
