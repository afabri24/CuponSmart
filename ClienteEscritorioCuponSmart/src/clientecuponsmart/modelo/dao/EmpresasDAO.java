/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientecuponsmart.modelo.dao;

import clientecuponsmart.modelo.ConexionWS;
import clientecuponsmart.modelo.pojo.Empresas;
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
public class EmpresasDAO {
    
    
    public static HashMap obtenerEmpresas(){
       HashMap<String,Object> respService= new LinkedHashMap<>();
       List<Empresas> empresas=null;
       String url=Constantes.URL_WS+"empresas/obtener";
       RespuestaHTTP respuesta= ConexionWS.peticionGET(url);
        if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            Gson gson=new Gson();
            Type tipoListaEmpresas = new TypeToken<List<Empresas>>(){}.getType();
            empresas =gson.fromJson(respuesta.getContenido(),tipoListaEmpresas);
            respService.put("Error", false);
            respService.put("empresas",empresas);
        }else{
            respService.put("error", true);
            respService.put("mensaje","hubo un error en la peticion, por el momento no se puede cargar la informacion de las empresas");
        }
        return respService;
    }
    
    
    public static Mensaje registrarEmpresa(Empresas empresaNueva){
        Mensaje msj=new Mensaje();
        String url = Constantes.URL_WS+"empresas/registrar";
        String parametros;
        parametros = String.format("nombre=%s&nombreComercial=%s&representanteLegal=%s&email=%s&direccion=%s&codigoPostal=%s&ciudad=%s&telefono=%s&paginaWeb=%s&RFC=%s&estatus=%s",
        empresaNueva.getNombre(), empresaNueva.getNombreComercial(), empresaNueva.getRepresentanteLegal(), empresaNueva.getEmail(),
        empresaNueva.getDireccion(), empresaNueva.getCodigoPostal(), empresaNueva.getCiudad(), empresaNueva.getTelefono(), empresaNueva.getPaginaWeb(),
        empresaNueva.getRFC(), empresaNueva.getEstatus());
        RespuestaHTTP respuesta=ConexionWS.peticionPOST(url, parametros);
        if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            Gson gson= new Gson();
            msj= gson.fromJson(respuesta.getContenido(), Mensaje.class);
        }else{
            msj.setError(true);
            msj.setMensaje("Error al enviar la informacion al registrar la empresa, intentelo mas tarde.");
        }
        return msj;
    }
    
    public static Mensaje editarEmpresa(Empresas empresaEdicion){
        Mensaje msj=new Mensaje();
        String url = Constantes.URL_WS+"empresas/editar";
        String parametros;
        parametros = String.format("idEmpresa=%d&nombre=%s&nombreComercial=%s&representanteLegal=%s&direccion=%s&codigoPostal=%s&ciudad=%s&telefono=%s&paginaWeb=%s&RFC=%s&estatus=%s",
        empresaEdicion.getIdEmpresa(), empresaEdicion.getNombre(), empresaEdicion.getNombreComercial(), empresaEdicion.getRepresentanteLegal(),
        empresaEdicion.getDireccion(), empresaEdicion.getCodigoPostal(), empresaEdicion.getCiudad(), empresaEdicion.getTelefono(), empresaEdicion.getPaginaWeb(),
        empresaEdicion.getRFC(), empresaEdicion.getEstatus());
        RespuestaHTTP respuesta=ConexionWS.peticionPUT(url, parametros);
        if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            Gson gson= new Gson();
            msj= gson.fromJson(respuesta.getContenido(), Mensaje.class);
        }else{
            msj.setError(true);
            msj.setMensaje("Error al enviar la informacion al editar la empresa, intentelo mas tarde.");
        }
        return msj;
    }
    
    public static Mensaje eliminarEmpresa(Empresas empresaEliminar){
        Mensaje msj=new Mensaje();
        
        String parametros;
        parametros = String.format("idEmpresa=%d",
                empresaEliminar.getIdEmpresa());
        String url = Constantes.URL_WS+"empresas/eliminar";
        RespuestaHTTP respuesta= ConexionWS.peticionDELETE(url,parametros);
         if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            Gson gson= new Gson();
            msj= gson.fromJson(respuesta.getContenido(), Mensaje.class);
        }else{
            msj.setError(true);
            msj.setMensaje("Error al enviar la informacion al eliminar la empresa, intentelo mas tarde.");
        }
        return msj;
    }
    
    public static Mensaje subirLogoEmpresa(int idEmpresa, byte[] fotografia){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS+"empresas/subirLogo/"+idEmpresa;
        RespuestaHTTP respuesta = ConexionWS.peticionPUTImagen(url, fotografia);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson=new Gson();
            msj=gson.fromJson(respuesta.getContenido(),Mensaje.class);
        }else{
            msj.setError(true);
            msj.setMensaje("Hubo un error al enviar la imagen del paciente,intentar de nuevo mas tarde");
        }
        return msj;
    }
    public static Empresas obtenerLogoEmpresa(int idEmpresa){
        Empresas empresa =null;
        String url =Constantes.URL_WS+"empresas/obtenerLogo/"+idEmpresa;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson=new Gson();
            empresa=gson.fromJson(respuesta.getContenido(),Empresas.class);
        }else{
            return empresa;
        }
        return empresa;
    }

}
