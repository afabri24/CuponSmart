/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.EmpresasDAO;
import modelo.pojo.Empresas;
import modelo.pojo.Mensaje;

/**
 *
 * @author afabri24
 */
@Path("empresas")
public class EmpresasWS {
    private UriInfo context;

    public EmpresasWS() {
    }
    @GET
    @Path("obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empresas> obtenerEmpresas() {
        List<Empresas> empresas = EmpresasDAO.obtenerEmpresas();
        if (empresas == null || empresas.isEmpty()) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }
        return empresas;
    }

    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarEmpresa(@FormParam("nombre") String nombre,
                                    @FormParam("nombreComercial") String nombreComercial,
                                    @FormParam("representanteLegal") String representanteLegal,
                                    @FormParam("email") String email,
                                    @FormParam("telefono") String telefono,
                                    @FormParam("paginaWeb") String paginaWeb,
                                    @FormParam("RFC") String RFC,
                                    @FormParam("estatus") int estatus,
                                    @FormParam("idDireccion") int idDireccion) {

        Mensaje mensaje;
        if (nombre != null && !nombre.isEmpty()) {
            mensaje = EmpresasDAO.registrarEmpresa(nombre,nombreComercial,representanteLegal,email,telefono,paginaWeb,RFC,estatus,idDireccion);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }

    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarEmpresa(@FormParam("idEmpresa") int idEmpresa,
                                 @FormParam("nombre") String nombre,
                                 @FormParam("nombreComercial") String nombreComercial,
                                 @FormParam("representanteLegal") String representanteLegal,
                                 @FormParam("email") String email,
                                 @FormParam("telefono") String telefono,
                                 @FormParam("paginaWeb") String paginaWeb,
                                 @FormParam("estatus") int estatus,
                                 @FormParam("idDireccion") int idDireccion) {

        Mensaje mensaje;
        if (idEmpresa > 0) {
            mensaje = EmpresasDAO.editarEmpresa(idEmpresa,nombre,nombreComercial,representanteLegal,email,telefono,paginaWeb,estatus,idDireccion);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }


    @DELETE
    @Path("eliminar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarEmpresa(@FormParam("idEmpresa") int idEmpresa) {
        Mensaje mensaje;
        if (idEmpresa > 0) {
            mensaje = EmpresasDAO.eliminarEmpresa(idEmpresa);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }

}
