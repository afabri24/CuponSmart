/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import modelo.pojo.Mensaje;
import modelo.pojo.Usuarios;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author ferna
 */
public class UsuariosDAO {

    public static Mensaje registrarUsuario(String nombre, String apellidoPaterno, String apellidoMaterno, String CURP, String correoElectronico, String username, String contrasenia, String rol, Integer idEmpresa) {
        Usuarios usuario = new Usuarios();
        usuario.setNombre(nombre);
        usuario.setApellidoPaterno(apellidoPaterno);
        usuario.setApellidoMaterno(apellidoMaterno);
        usuario.setCURP(CURP);
        usuario.setCorreoElectronico(correoElectronico);
        usuario.setUsername(username);
        usuario.setContrasenia(contrasenia);
        usuario.setRol(rol);
        usuario.setIdEmpresa(idEmpresa);
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        mensaje.setError(true);

        if (conexionBD != null) {
            try {
                Usuarios pacienteExiste = conexionBD.selectOne("usuarios.mostrarUsuarioPorCorreo", correoElectronico);
                if (pacienteExiste == null) {
                    int numFilasAfectadas = conexionBD.insert("usuarios.registrarUsuario", usuario);
                    conexionBD.commit();
                    if (numFilasAfectadas > 0) {
                        mensaje.setError(false);
                        mensaje.setMensaje("Usuario registrado");
                    } else {
                        mensaje.setMensaje("Hubo un error al registrar el Usuario, por favor de intentar mas tarde");
                    }
                } else {
                    mensaje.setMensaje("Usuario ya registrado");
                }
            } catch (Exception e) {
                mensaje.setMensaje("Error: " + e);
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setMensaje("hubo un error al conectarse a la base de datos por favor intentarlo mas tarde");
        }
        return mensaje;

    }
    public static Mensaje editarUsuario(Integer idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String CURP, String correoElectronico, String username, String contrasenia, String rol, Integer idEmpresa) {
        Usuarios usuario=new Usuarios();
        HashMap<String,Object> parametros = new HashMap<>();
        parametros.put("idUsuario",idUsuario);
        usuario.setNombre(nombre);
        usuario.setApellidoPaterno(apellidoPaterno);
        usuario.setApellidoMaterno(apellidoMaterno);
        usuario.setCURP(CURP);
        usuario.setCorreoElectronico(correoElectronico);
        usuario.setUsername(username);
        usuario.setRol(rol);
        usuario.setIdEmpresa(idEmpresa);
        
        Mensaje mensaje=new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        mensaje.setError(true);
        if(conexionBD != null){
            try {
                Usuarios usuarioExiste= conexionBD.selectOne("usuarios.mostrarUsuarioPorId",idUsuario);
                if(usuarioExiste!=null){
                    int numFilasAfectadas=conexionBD.insert("usuarios.editarUsuario", parametros);
                    conexionBD.commit();
                    if(numFilasAfectadas>0){
                        mensaje.setError(false);
                        mensaje.setMensaje("Usuario editado");
                    }else{
                       mensaje.setMensaje("Hubo un error al editar el Usuario, por favor de intentar mas tarde");
                    }
                }else{
                    mensaje.setMensaje("Paciente no encontrado");
                }
            }catch(Exception e){
            mensaje.setMensaje("Error: "+ e);
        }finally{
                conexionBD.close();
         }
        }else{
            mensaje.setMensaje("hubo un error al conectarse a la base de datos por favor intentarlo mas tarde");
        }
     return mensaje;
    }
    
     public static Mensaje eliminarUsuario(Integer idUsuario){
        Usuarios usuario =new Usuarios();
        Mensaje mensaje=new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        mensaje.setError(true);
        if(conexionBD != null){
            try {
                Usuarios usuarioExiste= conexionBD.selectOne("usuarios.mostrarUsuarioPorId",idUsuario);
                if(usuarioExiste!=null){
                    int numFilasAfectadas=conexionBD.insert("usuarios.eliminarUsuario", idUsuario);
                    conexionBD.commit();
                    if(numFilasAfectadas>0){
                        mensaje.setError(false);
                        mensaje.setMensaje("Usuario editado");
                    }else{
                       mensaje.setMensaje("Hubo un error al editar el Usuario, por favor de intentar mas tarde");
                    }
                }else{
                    mensaje.setMensaje("Paciente no encontrado");
                }
            }catch(Exception e){
            mensaje.setMensaje("Error: "+ e);
        }finally{
                conexionBD.close();
         }
        }else{
            mensaje.setMensaje("hubo un error al conectarse a la base de datos por favor intentarlo mas tarde");
        }
     return mensaje;
    }   
}
