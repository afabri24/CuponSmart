/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.LinkedHashMap;
import java.util.List;
import modelo.pojo.Empresas;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author ferna
 */
public class EmpresasDAO {
    
    public static List<Empresas> obtenerEmpresas() {
        List<Empresas> empresas = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                empresas = conexionBD.selectList("empresa.mostrarTodasEmpresas");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return empresas;
    }
    
    public static Empresas obtenerEmpresaPorId(int idEmpresas) {
        Empresas empresa = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                empresa = conexionBD.selectOne("empresa.mostrarEmpresaPorId", idEmpresas);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return empresa;
    }
    
    public static Empresas obtenerEmpresaPorRFC(String rfc) {
        Empresas empresa = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                empresa = conexionBD.selectOne("empresa.mostrarEmpresaPorRFC", rfc);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return empresa;
    }
    
    public static Mensaje registrarEmpresa(String nombre, String nombreComercial, String representanteLegal, String email, String telefono, String paginaWeb, String RFC, int estatus,int idDireccion) {
        Empresas empresa = new Empresas();
        empresa.setNombre(nombre);
        empresa.setNombreComercial(nombreComercial);
        empresa.setRepresentanteLegal(representanteLegal);
        empresa.setEmail(email);
        empresa.setTelefono(telefono);
        empresa.setPaginaWeb(paginaWeb);
        empresa.setRFC(RFC);
        empresa.setEstatus(estatus);
        empresa.setIdDireccion(idDireccion);
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        mensaje.setError(true);
        if (conexionBD != null) {
            try {
                Empresas empresaExiste = conexionBD.selectOne("empresa.mostrarEmpresaPorEmail", email);
                if (empresaExiste == null) {
                    int numFilasAfectadas = conexionBD.insert("empresa.registrar", empresa);
                    conexionBD.commit();
                    if (numFilasAfectadas > 0) {
                        mensaje.setError(false);
                        mensaje.setMensaje("Empresa registrada");
                    } else {
                        mensaje.setMensaje("Hubo un error al registrar el Empresa, por favor de intentar mas tarde");
                    }
                } else {
                    mensaje.setMensaje("Empresa ya registrado");
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
    
    public static Mensaje editarEmpresa(int idEmpresas, String nombre, String nombreComercial,
            String representanteLegal, String email, String telefono, String paginaWeb,
             int estatus, int idDireccion) {
        Empresas empresa = new Empresas();
        empresa.setIdEmpresa(idEmpresas);
        empresa.setNombre(nombre);
        empresa.setNombreComercial(nombreComercial);
        empresa.setRepresentanteLegal(representanteLegal);
        empresa.setEmail(email);
        empresa.setTelefono(telefono);
        empresa.setPaginaWeb(paginaWeb);
        empresa.setEstatus(estatus);
        empresa.setIdDireccion(idDireccion);

        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        mensaje.setError(true);
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.update("empresa.editarEmpresas", empresa);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Empresas editadas");
                } else {
                    mensaje.setMensaje("Hubo un error al editar las empresas, por favor inténtalo más tarde");
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

    public static Mensaje eliminarEmpresa(int idEmpresas) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int numFilasAfectadas = conexionBD.delete("empresa.eliminarEmpresas", idEmpresas);
                conexionBD.commit();
                if (numFilasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Empresas eliminadas");
                } else {
                    mensaje.setMensaje("Hubo un error al eliminar las empresas, por favor inténtalo más tarde");
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
    
    public static Mensaje guardarLogoEmpresa(int idEmpresa, byte[] logo) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                LinkedHashMap<String, Object> parametros = new LinkedHashMap<>();
                parametros.put("idEmpresa", idEmpresa);
                parametros.put("logo", logo);

                int filasAfectadas = conexionBD.update("empresa.guardarLogoEmpresa", parametros);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Logo de empresa guardado correctamente");
                } else {
                    mensaje.setMensaje("Hubo un error al guardar el logo de la empresa, por favor inténtalo más tarde");
                }
            } catch (Exception e) {
                mensaje.setMensaje("Error: " + e);
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setMensaje("Error en la conexión, por el momento no se puede guardar el logo de la empresa");
        }
        return mensaje;
    }

    public static Empresas obtenerLogoEmpresa(int idEmpresa) {
        Empresas empresa = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                empresa = conexionBD.selectOne("empresa.obtenerLogoEmpresa", idEmpresa);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return empresa;
    }
    
}
