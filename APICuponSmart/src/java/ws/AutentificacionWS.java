/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.AutenticacionDAO;
//import modelo.pojo.RespuestaLoginApp;
import modelo.pojo.RespuestaLoginEscritorio;

/**
 * REST Web Service
 *
 * @author afabri24
 */
@Path("autentificacion")
public class AutentificacionWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AutentificacionWS
     */
    public AutentificacionWS() {
    }
    
    @POST
    @Path("loginEscritorio")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLoginEscritorio loginEscritorio(@FormParam("username") String username,
                                  @FormParam("contrasenia") String contrasenia){
        RespuestaLoginEscritorio respuestaLogin = null;
        if(username != null && !username.isEmpty() && contrasenia != null && !contrasenia.isEmpty()){
            respuestaLogin=AutenticacionDAO.verificarSesionEscritorio(username,contrasenia);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return respuestaLogin;
    }
    /*
    @POST
    @Path("loginApp")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLoginApp loginApp(@FormParam("email")String email,@FormParam("contrasenia") String contrasenia){
        RespuestaLoginApp respuestaLogin=null;
        if(email !=null && !email.isEmpty()  && contrasenia != null && !contrasenia.isEmpty()){
            respuestaLogin=AutentificacionDAO.veficarSesionApp(email, contrasenia);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return respuestaLogin;
    }*/
}
