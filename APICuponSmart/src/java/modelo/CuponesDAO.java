/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import modelo.pojo.Cupones;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author afabri24
 */
public class CuponesDAO {
    public static List<Cupones> obtenerCuponesPorPromocion(int idPromocion) {
        List<Cupones> cupones = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                cupones = conexionBD.selectList("cupon.obtenerCuponesPorPromocion", idPromocion);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return cupones;
    }

    public static Cupones obtenerCuponPorId(int idCupon) {
        Cupones cupon = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                cupon = conexionBD.selectOne("cupon.obtenerCuponPorId", idCupon);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return cupon;
    }

    public static Mensaje registrarCupon(int idPromocion, Integer numeroCuponesDisponibles) {
        Cupones cupon = new Cupones();
        cupon.setIdPromocion(idPromocion);
        cupon.setNumeroCuponesDisponibles(numeroCuponesDisponibles);

        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        mensaje.setError(true);
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.insert("cupon.registrarCupon", cupon);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Cupón registrado");
                } else {
                    mensaje.setMensaje("Hubo un error al registrar el Cupón, por favor inténtalo más tarde");
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

    public static Mensaje eliminarCupon(int idCupon) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.delete("cupon.eliminarCupon", idCupon);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Cupón eliminado");
                } else {
                    mensaje.setMensaje("Hubo un error al eliminar el Cupón, por favor inténtalo más tarde");
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
