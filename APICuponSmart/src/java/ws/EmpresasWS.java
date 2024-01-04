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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
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
    @Context
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
    
    
    @GET
    @Path("obtenerEmpresaId/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Empresas obtenerEmpresaPorId(@PathParam("idEmpresa") Integer idEmpresa){
        Empresas empresa= null;
        if(idEmpresa!=null&&idEmpresa>0){
            empresa=EmpresasDAO.obtenerEmpresaPorId(idEmpresa);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return empresa;
    }

    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarEmpresa(@FormParam("nombre") String nombre,
                                    @FormParam("nombreComercial") String nombreComercial,
                                    @FormParam("representanteLegal") String representanteLegal,
                                    @FormParam("email") String email,
                                    @FormParam("telefono") String telefono,
                                    @FormParam("direccion") String direccion,
                                    @FormParam("codigoPostal") String codigoPostal,
                                    @FormParam("ciudad") String ciudad,
                                    @FormParam("paginaWeb") String paginaWeb,
                                    @FormParam("RFC") String RFC,
                                    @FormParam("estatus") String estatus) {

        Mensaje mensaje;
        if (nombre != null && !nombre.isEmpty()) {
            mensaje = EmpresasDAO.registrarEmpresa(nombre,nombreComercial,representanteLegal,email,telefono,paginaWeb,RFC,estatus,direccion,codigoPostal,ciudad);
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
                                 @FormParam("direccion") String direccion,
                                 @FormParam("codigoPostal") String codigoPostal,
                                 @FormParam("ciudad") String ciudad,
                                 @FormParam("telefono") String telefono,
                                 @FormParam("paginaWeb") String paginaWeb,
                                 @FormParam("estatus") String estatus) {

        Mensaje mensaje;
        if (idEmpresa > 0) {
            mensaje = EmpresasDAO.editarEmpresa(idEmpresa,nombre,nombreComercial,representanteLegal,telefono,paginaWeb,estatus,direccion,codigoPostal,ciudad);
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

    @PUT
    @Path("subirLogo/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje subirLogo(@PathParam("idEmpresa")Integer idEmpresa, byte[] foto){
        Mensaje msj=null;
        if(idEmpresa!=null){
            msj=EmpresasDAO.guardarLogoEmpresa(idEmpresa, foto);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return msj;
    }
    
    
    @GET
    @Path("obtenerLogo/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Empresas obtenerLogo(@PathParam("idEmpresa") Integer idEmpresa){
        Empresas empresa= null;
        if(idEmpresa!=null&&idEmpresa>0){
            empresa=EmpresasDAO.obtenerLogoEmpresa(idEmpresa);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return empresa;
    }

}
