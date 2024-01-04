/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.UsuariosDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.Usuarios;

/**
 *
 * @author ferna
 */
@Path("usuarios")
public class UsuariosWS {
    @Context
    private UriInfo context;
    public UsuariosWS() {
    }    
    
    @POST
    @Path("registrarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarUsuario(@FormParam("nombre")String nombre,
                                     @FormParam("apellidoPaterno")String apellidoPaterno,
                                     @FormParam("apellidoMaterno")String apeliidoMaterno,
                                     @FormParam("CURP")String CURP,
                                     @FormParam("correoElectronico")String correoElectronico,
                                     @FormParam("username") String username,
                                     @FormParam("contrasenia")String contrasenia,
                                     @FormParam("rol")int rol,
                                     @FormParam("idEmpresa")int idEmpresa){
        Usuarios usuario = null;
        Mensaje mensaje=null;
         if(correoElectronico != null && !correoElectronico.isEmpty() && contrasenia != null && !contrasenia.isEmpty()){
            mensaje=UsuariosDAO.registrarUsuario(nombre, apellidoPaterno, apeliidoMaterno, CURP, correoElectronico, username, contrasenia, rol, idEmpresa);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }
    @PUT
    @Path("editarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarPaciente(@FormParam("idUsuario")Integer idUsuario,
                                  @FormParam("nombre")String nombre,
                                    @FormParam("apellidoPaterno")String apellidoPaterno,
                                    @FormParam("apellidoMaterno")String apellidoMaterno,
                                    @FormParam("CURP")String CURP,
                                    @FormParam("correoElectronico")String correoElectronico,
                                    @FormParam("username") String username,
                                    @FormParam("contrasenia")String contrasenia,
                                    @FormParam("rol")int rol,
                                    @FormParam("idEmpresa")Integer idEmpresa){
        Usuarios usuario = null;
        Mensaje mensaje=null;
         if(idUsuario != null){
            mensaje=UsuariosDAO.editarUsuario(idUsuario, nombre, apellidoPaterno, apellidoMaterno, CURP, correoElectronico, username, contrasenia, rol, idEmpresa);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }
    @DELETE
    @Path("eliminarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarUsuario(@FormParam("idUsuario")Integer idUsuario){
        Usuarios usuario =null;
        Mensaje mensaje=null;
        if(idUsuario != null){
            mensaje=UsuariosDAO.eliminarUsuario(idUsuario);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }
    
}
