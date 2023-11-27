package clientecuponsmart.modelo.dao;


import clientecuponsmart.modelo.ConexionWS;
import clientecuponsmart.modelo.pojo.RespuestaHTTP;
import clientecuponsmart.modelo.pojo.RespuestaLogin;
import clientecuponsmart.utils.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;


/**
 *
 * @author afabri24
 */
public class InicioSesionDAO {
    
    public static RespuestaLogin IniciarSesion(String username,
                                       String contrasenia){
        RespuestaLogin respuesta=new RespuestaLogin();
        String url=Constantes.URL_WS+"autentificacion/loginEscritorio";
        String parametros=String.format("username=%s&contrasenia=%s",username,contrasenia);
        RespuestaHTTP respuestaPeticion = ConexionWS.peticionPOST(url, parametros);
        if(respuestaPeticion.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson=new Gson();
            respuesta= gson.fromJson(respuestaPeticion.getContenido(),RespuestaLogin.class);
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Hubo un error al procesar la solicitud, por favor intente mas tarde");
        }
        return respuesta;
    }
}
