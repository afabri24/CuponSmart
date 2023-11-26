/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.pojo.Empresas;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author ferna
 */
public class EmpresasDAO {
    
    public static Mensaje registrarEmpresa(String nombre, String nombreComercial, byte[] logo, String representanteLegal, String email, String telefono, String paginaWeb, String RFC, int estatus) {
        Empresas empresa = new Empresas();
        empresa.setNombre(nombre);
        empresa.setNombreComercial(nombreComercial);
        empresa.setLogo(logo);
        empresa.setRepresentanteLegal(representanteLegal);
        empresa.setEmail(email);
        empresa.setTelefono(telefono);
        empresa.setPaginaWeb(paginaWeb);
        empresa.setRFC(RFC);
        empresa.setEstatus(estatus);
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
}
