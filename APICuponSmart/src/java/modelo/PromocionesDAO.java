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
import modelo.pojo.PromocionesSucursales;
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
            String fechaTermino, String restricciones, String tipo, float valor, String categoria,
            Integer cuponesMaximos, String codigo, String estatus, int idEmpresa) {
        Promociones promocion = new Promociones();
        promocion.setNombre(nombre);
        promocion.setDescripcion(descripcion);
        promocion.setFechaInicio(fechaInicio);
        promocion.setFechaTermino(fechaTermino);
        promocion.setRestricciones(restricciones);
        promocion.setTipo(tipo);
        promocion.setValor(valor);
        promocion.setCategoria(categoria);
        promocion.setCuponesMaximos(cuponesMaximos);
        promocion.setCodigo(codigo);
        promocion.setEstatus(estatus);
        promocion.setIdEmpresa(idEmpresa);
        

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
        String fechaInicio, String fechaTermino, String restricciones, String tipo, float valor,
        String categoria, Integer cuponesMaximos, String estatus) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idPromocion", idPromocion);
        parametros.put("nombre", nombre);
        parametros.put("descripcion", descripcion);
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaTermino", fechaTermino);
        parametros.put("restricciones", restricciones);
        parametros.put("tipo", tipo);
        parametros.put("valor", valor);
        parametros.put("categoria", categoria);
        parametros.put("cuponesMaximos", cuponesMaximos);
        parametros.put("estatus", estatus);

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
    
    public static Mensaje eliminarPromocion(int idPromocion) {
        Mensaje mensaje = null;
         mensaje.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.delete("promocion.eliminarPromocion", idPromocion);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Promocion eliminada");
                } else {
                    mensaje.setMensaje("Hubo un error al eliminar la promocion, por favor inténtalo más tarde");
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

                int filasAfectadas = conexionBD.update("promocion.guardarImagenPromocion", parametros);
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
    
    public static Mensaje registrarPromocionesSucursales(int idPromocion,int idSucursal ) {
        PromocionesSucursales promocionsucursal = new PromocionesSucursales();
        promocionsucursal.setIdPromocion(idPromocion);
        promocionsucursal.setIdSucursal(idSucursal);
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.insert("insertarPromocionSucursal", promocionsucursal);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("PromociónSucursal registrada");
                } else {
                    mensaje.setMensaje("Hubo un error al registrar la PromociónSucursal, por favor inténtalo más tarde");
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
    
    public static List<Promociones> obtenerPromocionesPorSucursal(int idSucursal) {
        List<Promociones> promociones = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                promociones = conexionBD.selectList("promocion.obtenerPromocionesSucursales", idSucursal);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return promociones;
    }
}
