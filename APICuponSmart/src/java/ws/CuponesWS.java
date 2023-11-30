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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.CuponesDAO;
import modelo.pojo.Cupones;
import modelo.pojo.Mensaje;

/**
 *
 * @author afabri24
 */
public class CuponesWS {
    @Context
    private UriInfo context;

    public CuponesWS() {
    }

    @GET
    @Path("obtener/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cupones> obtenerCuponesPromocion(@PathParam("idPromocion")int idPromocion) {
        List<Cupones> cupones = null;
        cupones = CuponesDAO.obtenerCuponesPorPromocion(idPromocion);
        if (cupones == null) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }
        return cupones;
    }

    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarCupon(@FormParam("idPromocion") int idPromocion,
                                  @FormParam("numeroCuponesDisponibles") Integer numeroCuponesDisponibles) {
        Mensaje mensaje = null;
        if (idPromocion > 0 && numeroCuponesDisponibles != null && numeroCuponesDisponibles >= 0) {
            mensaje = CuponesDAO.registrarCupon(idPromocion, numeroCuponesDisponibles);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }


    @DELETE
    @Path("eliminar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarCupon(@FormParam("idCupon") int idCupon) {
        Mensaje mensaje = null;
        if (idCupon > 0) {
            mensaje = CuponesDAO.eliminarCupon(idCupon);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }
}
