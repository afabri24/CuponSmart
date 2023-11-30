/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import modelo.pojo.Mensaje;
import modelo.pojo.Promociones;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author afabri24
 */
public class PromocionesDAO {
        


    public static List<Promociones> obtenerPromocionesPorEmpresa(int idEmpresa) {
        List<Promociones> promociones = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                promociones = conexionBD.selectList("promocion.obtenerPromocionesPorEmpresa", idEmpresa);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return promociones;
    }

    public static Promociones obtenerPromocionPorId(int idPromocion) {
        Promociones promocion = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                promocion = conexionBD.selectOne("promocion.obtenerPromocionPorId", idPromocion);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return promocion;
    }

    public static Mensaje registrarPromocion(String nombre, String descripcion, String fechaInicio,
            String fechaTermino, String restricciones, int tipoPromocion, float valorPromocion, String categoria,
            Integer cuponesMaximos, String codigoPromocion, int estatus, int idEmpresa, int idSucursal) {
        Promociones promocion = new Promociones();
        promocion.setNombre(nombre);
        promocion.setDescripcion(descripcion);
        promocion.setFechaInicio(fechaInicio);
        promocion.setFechaTermino(fechaTermino);
        promocion.setRestricciones(restricciones);
        promocion.setTipoPromocion(tipoPromocion);
        promocion.setValorPromocion(valorPromocion);
        promocion.setCategoria(categoria);
        promocion.setCuponesMaximos(cuponesMaximos);
        promocion.setCodigoPromocion(codigoPromocion);
        promocion.setEstatus(estatus);
        promocion.setIdEmpresa(idEmpresa);
        promocion.setIdSucursal(idSucursal);

        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.insert("promocion.registrarPromocion", promocion);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Promoción registrada");
                } else {
                    mensaje.setMensaje("Hubo un error al registrar la Promoción, por favor inténtalo más tarde");
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

    public static Mensaje editarPromocion(int idPromocion, String nombre, String descripcion,
        String fechaInicio, String fechaTermino, String restricciones, int tipoPromocion, float valorPromocion,
        String categoria, Integer cuponesMaximos, String codigoPromocion, int estatus, int idEmpresa,
        int idSucursal) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idPromocion", idPromocion);
        parametros.put("nombre", nombre);
        parametros.put("descripcion", descripcion);
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaTermino", fechaTermino);
        parametros.put("restricciones", restricciones);
        parametros.put("tipoPromocion", tipoPromocion);
        parametros.put("valorPromocion", valorPromocion);
        parametros.put("categoria", categoria);
        parametros.put("cuponesMaximos", cuponesMaximos);
        parametros.put("codigoPromocion", codigoPromocion);
        parametros.put("estatus", estatus);
        parametros.put("idEmpresa", idEmpresa);
        parametros.put("idSucursal", idSucursal);

        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        mensaje.setError(true);

        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.update("promocion.editarPromocionPorHashMap", parametros);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Promoción editada");
                } else {
                    mensaje.setMensaje("Hubo un error al editar la Promoción, por favor inténtalo más tarde");
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

    public static Mensaje subirImagenPromocion(int idPromocion, byte[] imagen) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                LinkedHashMap<String, Object> parametros = new LinkedHashMap<>();
                parametros.put("idPromocion", idPromocion);
                parametros.put("imagen", imagen);

                int filasAfectadas = conexionBD.update("promocion.subirImagenPromocion", parametros);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Imagen de promoción subida correctamente");
                } else {
                    mensaje.setMensaje("Hubo un error al subir la imagen de la promoción, por favor inténtalo más tarde");
                }
            } catch (Exception e) {
                mensaje.setMensaje("Error: " + e);
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setMensaje("Error en la conexión, por el momento no se puede subir la imagen de la promoción");
        }
        return mensaje;
    }

    public static Promociones obtenerImagenPromocion(int idPromocion) {
        Promociones promocion = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                promocion = conexionBD.selectOne("promocion.obtenerImagenPromocion", idPromocion);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return promocion;
    }
}
