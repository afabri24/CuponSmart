/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import modelo.pojo.RespuestaLoginEscritorio;
import modelo.pojo.Usuarios;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author ferna
 */
public class AutenticacionDAO {
    public static RespuestaLoginEscritorio verificarSesionEscritorio(String username,
                                                                     String contrasenia){
        RespuestaLoginEscritorio respuesta = new RespuestaLoginEscritorio();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                HashMap<String,String> parametros = new HashMap<>();
                parametros.put("username", username);
                parametros.put("contrasenia", contrasenia);
                Usuarios usuariosSesion = conexionBD.selectOne("autentificacion.loginEscritorio", parametros);
                if(usuariosSesion != null){
                    respuesta.setError(false);
                    respuesta.setMensaje("Bienvenido(a) "+usuariosSesion.getNombre()+" al sistema de administracion");
                    respuesta.setUsuariosSesion(usuariosSesion);
                }else{
                    respuesta.setMensaje("Numero de personal y/o contraseña incorrectas, favor de intentar de nuevo");
                }
            }catch (Exception e){
                respuesta.setMensaje("Error: "+ e.getMessage());
            }
        }else {
            respuesta.setMensaje("Error de conexion con la base de datos.");
        }
        return respuesta;
    }
    /*
    public static RespuestaLoginApp veficarSesionApp(String email,String contrasenia){
        RespuestaLoginApp respuesta =new RespuestaLoginApp();
         respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                HashMap<String,String> parametros = new HashMap<>();
                parametros.put("email", email);
                parametros.put("contrasenia", contrasenia);
                Paciente pacienteSesion = conexionBD.selectOne("autentificacion.loginApp", parametros);
                if(pacienteSesion != null){
                    respuesta.setError(false);
                    respuesta.setMensaje("Bienvenido(a) "+pacienteSesion.getNombre()+" a la aplicacion de nutricion");
                    respuesta.setPacienteSesion(pacienteSesion);
                }else{
                    respuesta.setMensaje("Corro electronico y/o contraseña incorrectas, favor de intentar de nuevo");
                }
            }catch (Exception e){
                respuesta.setMensaje("Error: "+ e.getMessage());
            }
        }else {
            respuesta.setMensaje("Error de conexion con la base de datos.");
        }
        return respuesta;
    }*/
    
}
