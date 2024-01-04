/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientecuponsmart;

import clientecuponsmart.modelo.dao.DireccionesDAO;
import clientecuponsmart.modelo.pojo.Direcciones;
import clientecuponsmart.modelo.pojo.Mensaje;
import clientecuponsmart.utils.Utilidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author afabri24
 */
public class FXMLFormularioDireccionesController implements Initializable {

    private Direcciones direccionModificar;
    
    private int bandEditar=0;
    
    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfNumero;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfColonia;
    @FXML
    private Label lbErrorCiudad;
    @FXML
    private Label lbErrorCalle;
    @FXML
    private Label lbErrorNumero;
    @FXML
    private Label lbErrorColonia;
    @FXML
    private Label lbErrorCodigoPostal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGuardar(ActionEvent event) {
        Direcciones direccionNueva=new Direcciones();
        if(comprobarVacios()){
            direccionNueva.setCalle(tfCalle.getText());
            direccionNueva.setNumero(tfNumero.getText());
            direccionNueva.setColonia(tfColonia.getText());
            direccionNueva.setCiudad(tfCiudad.getText());
            direccionNueva.setCodigoPostal(tfCodigoPostal.getText());
            if(bandEditar==1){
                direccionNueva.setIdDireccion(direccionModificar.getIdDireccion());
                editarDireccion(direccionNueva);
            }else{
                guardarDireccion(direccionNueva);
            }
        }
    }
    
    public void inicializarInformacionDireccion(Direcciones direccionModificar){
        this.direccionModificar=direccionModificar;
        if(direccionModificar!=null){
            bandEditar=1;
            tfCalle.setText(direccionModificar.getCalle());
            tfNumero.setText(direccionModificar.getNumero());
            tfCiudad.setText(direccionModificar.getCiudad());
            tfColonia.setText(direccionModificar.getColonia());
            tfCodigoPostal.setText(direccionModificar.getCodigoPostal());
        }
    }
    
    public boolean comprobarVacios() {
        if ("".equals(tfCalle.getText())) {
            lbErrorCalle.setText("Calle requerida");
            return false;
        }
        if ("".equals(tfNumero.getText())) {
            lbErrorNumero.setText("Número requerido");
            return false;
        }
        if ("".equals(tfCodigoPostal.getText())) {
            lbErrorCodigoPostal.setText("Código postal requerido");
            return false;
        }
        if ("".equals(tfCiudad.getText())) {
            lbErrorCiudad.setText("Ciudad requerida");
            return false;
        }
        if ("".equals(tfColonia.getText())) {
            lbErrorColonia.setText("Colonia requerida");
            return false;
        }

        return true;
    }

    private void editarDireccion(Direcciones direccionNueva) {
       Mensaje msj=DireccionesDAO.editarDirecciones(direccionNueva);
       if(!msj.isError()){
           Utilidades.mostrarAlertaSimple("Direccion editada", msj.getMensaje(), Alert.AlertType.INFORMATION);
           cerrarVentana();
       }else{
            Utilidades.mostrarAlertaSimple("Error al editar", msj.getMensaje(), Alert.AlertType.ERROR);
       }
    }

    private void guardarDireccion(Direcciones direccionNueva) {
        Mensaje msj=DireccionesDAO.registrarDireccion(direccionNueva);
       if(!msj.isError()){
           Utilidades.mostrarAlertaSimple("Direccion Creada", msj.getMensaje(), Alert.AlertType.INFORMATION);
           cerrarVentana();
       }else{
            Utilidades.mostrarAlertaSimple("Error al guardar", msj.getMensaje(), Alert.AlertType.ERROR);
       }
    }
    
    
    private void cerrarVentana(){
        Stage escenario = (Stage) tfCalle.getScene().getWindow();
        escenario.close();
    }
    
    
}
