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
import modelo.DireccionesDAO;
import modelo.pojo.Direcciones;
import modelo.pojo.Mensaje;

/**
 *
 * @author afabri24
 */
public class DireccionesWS {
    @Context
    private UriInfo context;

    public DireccionesWS() {
    }

    @GET
    @Path("obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Direcciones> obtenerDirecciones() {
        List<Direcciones> direcciones = null;
        direcciones = DireccionesDAO.obtenerDirecciones();
        if (direcciones == null) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }
        return direcciones;
    }

    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarDireccion(@FormParam("calle") String calle,
                                      @FormParam("numero") String numero,
                                      @FormParam("codigoPostal") String codigoPostal,
                                      @FormParam("ciudad") String ciudad,
                                      @FormParam("colonia") String colonia) {
        Direcciones direccion=null;
        direccion.setCalle(calle);
        direccion.setNumero(numero);
        direccion.setCodigoPostal(codigoPostal);
        direccion.setCiudad(ciudad);
        direccion.setColonia(colonia);
        Mensaje mensaje = null;
        if (calle != null && !calle.isEmpty()) {
            mensaje = DireccionesDAO.registrarDireccion(direccion);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }

    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarDireccion(@FormParam("idDireccion") int idDireccion,
                                   @FormParam("calle") String calle,
                                   @FormParam("numero") String numero,
                                   @FormParam("codigoPostal") String codigoPostal,
                                   @FormParam("ciudad") String ciudad,
                                   @FormParam("colonia") String colonia) {
        Direcciones direccion=null;
        direccion.setCalle(calle);
        direccion.setNumero(numero);
        direccion.setCodigoPostal(codigoPostal);
        direccion.setCiudad(ciudad);
        direccion.setColonia(colonia);
        Mensaje mensaje = null;
        if (idDireccion > 0) {
            mensaje = DireccionesDAO.editarDireccion(direccion);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }

    @DELETE
    @Path("eliminar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarDireccion(@FormParam("idDireccion") int idDireccion) {
        Mensaje mensaje = null;
        if (idDireccion > 0) {
            mensaje = DireccionesDAO.eliminarDireccion(idDireccion);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }
}

