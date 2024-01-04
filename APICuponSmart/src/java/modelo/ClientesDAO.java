/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import java.util.Map;
import modelo.pojo.Clientes;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author afabri24
 */
public class ClientesDAO {
    public static Mensaje registrarCliente(String nombre, String apellidoPaterno, String apellidoMaterno,
         String telefono, String correoElectronico, String fechaNacimiento, String contrasenia, String Direccion) {
        Clientes cliente = new Clientes();
        cliente.setNombre(nombre);
        cliente.setApellidoPaterno(apellidoPaterno);
        cliente.setApellidoMaterno(apellidoMaterno);
        cliente.setTelefono(telefono);
        cliente.setCorreoElectronico(correoElectronico);
        cliente.setFechaNacimiento(fechaNacimiento);
        cliente.setContrasenia(contrasenia);
        cliente.setContrasenia(Direccion);

        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        mensaje.setError(true);
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.insert("cliente.registrarCliente", cliente);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Cliente registrado");
                } else {
                    mensaje.setMensaje("Hubo un error al registrar el Cliente, por favor inténtalo más tarde");
                }
            } catch (Exception e) {
                mensaje.setMensaje("Error: " + e);
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setMensaje("Hubo un error al conectarse a la base de datos, por favor inténtalo más tarde");
        }
        return mensaje;
    }

        public static Mensaje editarCliente(int idCliente, String nombre, String apellidoPaterno, String apellidoMaterno,
            String telefono, String fechaNacimiento, String contrasenia, String direccion) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idCliente", idCliente);
        parametros.put("nombre", nombre);
        parametros.put("apellidoPaterno", apellidoPaterno);
        parametros.put("apellidoMaterno", apellidoMaterno);
        parametros.put("telefono", telefono);
        parametros.put("fechaNacimiento", fechaNacimiento);
        parametros.put("contrasenia", contrasenia);
        parametros.put("direccion", direccion);

        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);

        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.update("cliente.editarClientePorHashMap", parametros);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Cliente editado");
                } else {
                    mensaje.setMensaje("Hubo un error al editar el Cliente, por favor inténtalo más tarde");
                }
            } catch (Exception e) {
                mensaje.setMensaje("Error: " + e);
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setMensaje("Hubo un error al conectarse a la base de datos, por favor inténtalo más tarde");
        }
        return mensaje;
    }

    public static Mensaje eliminarCliente(int idCliente) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.delete("cliente.eliminarCliente", idCliente);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Cliente eliminado");
                } else {
                    mensaje.setMensaje("Hubo un error al eliminar el Cliente, por favor inténtalo más tarde");
                }
            } catch (Exception e) {
                mensaje.setMensaje("Error: " + e);
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setMensaje("Hubo un error al conectarse a la base de datos, por favor inténtalo más tarde");
        }
        return mensaje;
    }
}

