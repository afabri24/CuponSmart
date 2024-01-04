/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import java.util.Random;
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
import modelo.CuponesDAO;
import modelo.pojo.Cupones;
import modelo.pojo.Mensaje;

/**
 *
 * @author afabri24
 */
@Path("cupones")
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
                                  @FormParam("estatus") String estatus,
                                  @FormParam("idPromocion") int idCliente) {
        Mensaje mensaje = null;
        // Generar el código del cupón
        String codigo = generarCodigoAlfanumerico();
        Cupones cupon=CuponesDAO.obtenerCuponPorCodigo(codigo);
        if(cupon==null){
            mensaje = CuponesDAO.registrarCupon(idPromocion,codigo, estatus,idCliente);
        }else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        
        return mensaje;
    }
    
    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarCupon(@FormParam("idCupon") int idCupon,
                                @FormParam("estatus") String estatus) {
        Mensaje mensaje = null;
       if(idCupon!=0){
           mensaje = CuponesDAO.editarCupon(idCupon, estatus);
       }else {
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
    
    
    public static String generarCodigoAlfanumerico() {

        // Generar un conjunto de caracteres alfanuméricos
        char[] caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        // Generar un código de 6 caracteres
        String codigo = "";
        for (int i = 0; i < 6; i++) {
            codigo += caracteres[new Random().nextInt(caracteres.length)];
        }

        return codigo;
    }
}
