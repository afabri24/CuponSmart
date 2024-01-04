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
import modelo.pojo.Sucursales;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author afabri24
 */
public class SucursalesDAO {

    public static List<Sucursales> obtenerSucursales() {
        List<Sucursales> sucursales = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                sucursales = conexionBD.selectList("sucursal.obtenerTodasSucursales");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return sucursales;
    }

    public static Sucursales obtenerSucursalPorId(int idSucursal) {
        Sucursales sucursal = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                sucursal = conexionBD.selectOne("sucursal.obtenerSucursalPorId", idSucursal);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return sucursal;
    }
    
    public static Sucursales obtenerSucursalesPorEmpresa(int idEmpresa) {
        Sucursales sucursal = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                sucursal = conexionBD.selectOne("sucursal.obtenerSucursalesPorEmpresa", idEmpresa);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return sucursal;
    }

    public static Mensaje registrarSucursal(String nombre, String telefono, float latitud,
            float longitud, String nombreEncargado, int idEmpresa, String direccion,String codigoPostal,String ciudad) {
        Sucursales sucursal = new Sucursales();
        sucursal.setNombre(nombre);
        sucursal.setTelefono(telefono);
        sucursal.setDireccion(direccion);
        sucursal.setCodigoPostal(codigoPostal);
        sucursal.setCiudad(ciudad);
        sucursal.setLatitud(latitud);
        sucursal.setLongitud(longitud);
        sucursal.setNombreEncargado(nombreEncargado);
        sucursal.setIdEmpresa(idEmpresa);
        

        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        mensaje.setError(true);
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.insert("sucursal.registrar", sucursal);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Sucursal registrada");
                } else {
                    mensaje.setMensaje("Hubo un error al registrar la Sucursal, por favor inténtalo más tarde");
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

    public static Mensaje editarSucursal(int idSucursal, String nombre, String telefono, float latitud,
            float longitud, String nombreEncargado,String direccion,String codigoPostal,String ciudad) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idSucursal", idSucursal);
        parametros.put("nombre", nombre);
        parametros.put("telefono", telefono);
        parametros.put("direccion", direccion);
        parametros.put("codigoPostal", codigoPostal);
        parametros.put("ciudad", ciudad);
        parametros.put("latitud", latitud);
        parametros.put("longitud", longitud);
        parametros.put("nombreEncargado", nombreEncargado);
        

        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        mensaje.setError(true);

        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.update("sucursal.editarSucursalPorHashMap", parametros);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Sucursal editada");
                } else {
                    mensaje.setMensaje("Hubo un error al editar la Sucursal, por favor inténtalo más tarde");
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

    public static Mensaje eliminarSucursal(int idSucursal) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.delete("sucursal.eliminarSucursal", idSucursal);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Sucursal eliminada");
                } else {
                    mensaje.setMensaje("Hubo un error al eliminar la Sucursal, por favor inténtalo más tarde");
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
    
    public static Mensaje subirImagenSucursal(int idSucursal, byte[] imagen) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        LinkedHashMap<String, Object> parametros = new LinkedHashMap<>();
        parametros.put("idSucursal", idSucursal);
        parametros.put("imagen", imagen);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("sucursal.subirImagenSucursal", parametros);
                conexionBD.commit();
                if (filasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Imagen de sucursal subida correctamente");
                } else {
                    mensaje.setMensaje("Hubo un error al subir la imagen de la sucursal, por favor inténtalo más tarde");
                }
            } catch (Exception e) {
                mensaje.setMensaje("Error: " + e);
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setMensaje("Error en la conexión, por el momento no se puede subir la imagen de la sucursal");
        }
        return mensaje;
    }
    
    public static Sucursales obtenerImagenSucursal(int idSucursal) {
        Sucursales sucursal = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                sucursal = conexionBD.selectOne("promocion.obtenerImagenSurcusal", idSucursal);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return sucursal;
    }
}

