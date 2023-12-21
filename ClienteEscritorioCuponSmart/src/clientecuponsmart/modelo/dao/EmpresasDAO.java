/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientecuponsmart.modelo.dao;

import clientecuponsmart.modelo.ConexionWS;
import clientecuponsmart.modelo.pojo.Empresas;
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
}
