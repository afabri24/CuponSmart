/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientecuponsmart.modelo.dao;

import clientecuponsmart.modelo.ConexionWS;
import clientecuponsmart.modelo.pojo.Direcciones;
import clientecuponsmart.modelo.pojo.Mensaje;
import clientecuponsmart.modelo.pojo.RespuestaHTTP;
import clientecuponsmart.utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author afabri24
 */
public class DireccionesDAO {
    
    
    public static Direcciones obtenerDireccionPorId(int idDireccion) {
        Direcciones direccion = new Direcciones();
        String url = Constantes.URL_WS + "direcciones/obtenerPorId/" + idDireccion;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type tipoDireccion = new TypeToken<Direcciones>() {}.getType();
            direccion = gson.fromJson(respuesta.getContenido(), tipoDireccion);
        }
        return direccion;
    }
    
    public static Mensaje registrarDireccion(Direcciones direccionNueva) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "direcciones/registrar";
        String parametros;
        parametros = String.format("calle=%s&numero=%s&codigoPostal=%s&ciudad=%s&colonia=%s",
                direccionNueva.getCalle(), direccionNueva.getNumero(), direccionNueva.getCodigoPostal(), direccionNueva.getCiudad(), direccionNueva.getColonia());
        RespuestaHTTP respuesta = ConexionWS.peticionPOST(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error al enviar la información al registrar la direccion, intentelo mas tarde.");
        }
        return msj;
    }

    public static Mensaje editarDirecciones(Direcciones direccionEdicion) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "direcciones/editar";
        String parametros;

        parametros = String.format("idDireccion=%d&calle=%s&numero=%s&codigoPostal=%s&ciudad=%s&colonia=%s",
                direccionEdicion.getIdDireccion(), direccionEdicion.getCalle(), direccionEdicion.getNumero(), direccionEdicion.getCodigoPostal(), direccionEdicion.getCiudad(), direccionEdicion.getColonia());
        RespuestaHTTP respuesta = ConexionWS.peticionPUT(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error al enviar la información al editar la dirección, intentelo mas tarde.");
        }
        return msj;
    }


}
