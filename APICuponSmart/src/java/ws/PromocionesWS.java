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
import modelo.PromocionesDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.Promociones;

/**
 *
 * @author afabri24
 */
public class PromocionesWS {
    @Context
    private UriInfo context;

    public PromocionesWS() {
    }

    @GET
    @Path("obtener/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promociones> obtenerPromociones(@PathParam("idEmpresa")int idEmpresa) {
        List<Promociones> promociones = null;
        promociones = PromocionesDAO.obtenerPromocionesPorEmpresa(idEmpresa);
        if (promociones == null) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }
        return promociones;
    }

    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarPromocion(@FormParam("nombre") String nombre,
                                      @FormParam("descripcion") String descripcion,
                                      @FormParam("fechaInicio") String fechaInicio,
                                      @FormParam("fechaTermino") String fechaTermino,
                                      @FormParam("restricciones") String restricciones,
                                      @FormParam("tipoPromocion") int tipoPromocion,
                                      @FormParam("valorPromocion") float valorPromocion,
                                      @FormParam("categoria") String categoria,
                                      @FormParam("cuponesMaximos") Integer cuponesMaximos,
                                      @FormParam("codigoPromocion") String codigoPromocion,
                                      @FormParam("estatus") int estatus,
                                      @FormParam("idEmpresa") int idEmpresa,
                                      @FormParam("idSucursal") int idSucursal) {
        Mensaje mensaje = null;
        if (nombre != null && !nombre.isEmpty()) {
            mensaje = PromocionesDAO.registrarPromocion(nombre, descripcion, fechaInicio, fechaTermino,
                    restricciones, tipoPromocion, valorPromocion, categoria, cuponesMaximos, codigoPromocion,
                    estatus, idEmpresa, idSucursal);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }
    
    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarPromocion(@FormParam("idPromocion") Integer idPromocion,
                                   @FormParam("nombre") String nombre,
                                   @FormParam("descripcion") String descripcion,
                                   @FormParam("fechaInicio") String fechaInicio,
                                   @FormParam("fechaTermino") String fechaTermino,
                                   @FormParam("restricciones") String restricciones,
                                   @FormParam("tipoPromocion") int tipoPromocion,
                                   @FormParam("valorPromocion") float valorPromocion,
                                   @FormParam("categoria") String categoria,
                                   @FormParam("cuponesMaximos") Integer cuponesMaximos,
                                   @FormParam("codigoPromocion") String codigoPromocion,
                                   @FormParam("estatus") int estatus,
                                   @FormParam("idEmpresa") int idEmpresa,
                                   @FormParam("idSucursal") int idSucursal) {
        Mensaje mensaje = null;
        if (idPromocion != null) {
            mensaje = PromocionesDAO.editarPromocion(idPromocion, nombre, descripcion, fechaInicio, fechaTermino,
                    restricciones, tipoPromocion, valorPromocion, categoria, cuponesMaximos, codigoPromocion,
                    estatus, idEmpresa, idSucursal);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }


    @DELETE
    @Path("eliminar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPromocion(@FormParam("idPromocion") Integer idPromocion) {
        Mensaje mensaje = null;
        if (idPromocion != null) {
            mensaje = PromocionesDAO.eliminarPromocion(idPromocion);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }

}

