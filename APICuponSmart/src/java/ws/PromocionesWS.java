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
import modelo.EmpresasDAO;
import modelo.PromocionesDAO;
import modelo.pojo.Empresas;
import modelo.pojo.Mensaje;
import modelo.pojo.Promociones;

/**
 *
 * @author afabri24
 */
@Path("promociones")
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
                                      @FormParam("tipo") String tipo,
                                      @FormParam("valor") float valor,
                                      @FormParam("categoria") String categoria,
                                      @FormParam("cuponesMaximos") Integer cuponesMaximos,
                                      @FormParam("estatus") String estatus,
                                      @FormParam("idEmpresa") int idEmpresa) {
        Mensaje mensaje = null;
        String codigo=generarCodigoAlfanumerico();
        if (nombre != null && !nombre.isEmpty()) {
            mensaje = PromocionesDAO.registrarPromocion(nombre, descripcion, fechaInicio, fechaTermino,
                    restricciones, tipo, valor, categoria, cuponesMaximos, codigo,
                    estatus, idEmpresa);
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
                                   @FormParam("tipo") String tipo,
                                   @FormParam("valor") float valor,
                                   @FormParam("categoria") String categoria,
                                   @FormParam("cuponesMaximos") Integer cuponesMaximos,
                                   @FormParam("estatus") String estatus,
                                   @FormParam("idEmpresa") int idEmpresa) {
        Mensaje mensaje = null;
        if (idPromocion != null) {
            mensaje = PromocionesDAO.editarPromocion(idPromocion, nombre, descripcion, fechaInicio, fechaTermino,
                    restricciones, tipo, valor, categoria, cuponesMaximos,
                    estatus);
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
    
    
    @PUT
    @Path("subirimagen/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje subirLogo(@PathParam("idPromocion")Integer idPromocion, byte[] foto){
        Mensaje msj=null;
        if(idPromocion!=null){
            msj=PromocionesDAO.subirImagenPromocion(idPromocion, foto);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return msj;
    }
    
    
    @GET
    @Path("obtenerImagen/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Promociones obtenerImagen(@PathParam("idPromocion") Integer idPromocion){
        Promociones promocion= null;
        if(idPromocion!=null&&idPromocion>0){
            promocion=PromocionesDAO.obtenerImagenPromocion(idPromocion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return promocion;
    }
    
    public static String generarCodigoAlfanumerico() {

        // Generar un conjunto de caracteres alfanuméricos
        char[] caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        // Generar un código de 8 caracteres
        String codigo = "";
        for (int i = 0; i < 8; i++) {
            codigo += caracteres[new Random().nextInt(caracteres.length)];
        }

        return codigo;
    }

}

