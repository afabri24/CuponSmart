/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import modelo.pojo.Direcciones;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author afabri24
 */
public class DireccionesDAO {

    public static List<Direcciones> obtenerDirecciones() {
        List<Direcciones> direcciones = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                direcciones = conexionBD.selectList("direccion.obtenerTodasDirecciones");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return direcciones;
    }

    public static Direcciones obtenerDireccionPorId(int idDireccion) {
        Direcciones direccion = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                direccion = conexionBD.selectOne("direccion.obtenerDireccionPorId", idDireccion);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return direccion;
    }

    public static Mensaje registrarDireccion(Direcciones direccion) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        mensaje.setError(true);
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.insert("direccion.registrarDireccion", direccion);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Dirección registrada");
                } else {
                    mensaje.setMensaje("Hubo un error al registrar la Dirección, por favor inténtalo más tarde");
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

    public static Mensaje editarDireccion(Direcciones direccion) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        mensaje.setError(true);
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.update("direccion.editarDireccion", direccion);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Dirección editada");
                } else {
                    mensaje.setMensaje("Hubo un error al editar la Dirección, por favor inténtalo más tarde");
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

    public static Mensaje eliminarDireccion(int idDireccion) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.delete("direccion.eliminarDireccion", idDireccion);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Dirección eliminada");
                } else {
                    mensaje.setMensaje("Hubo un error al eliminar la Dirección, por favor inténtalo más tarde");
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


