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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.ClientesDAO;
import modelo.pojo.Mensaje;

/**
 *
 * @author afabri24
 */
@Path("clientes")
public class ClientesWS {
    @Context
    private UriInfo context;

    public ClientesWS() {
    }


    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarCliente(@FormParam("nombre") String nombre,
                                    @FormParam("apellidoPaterno") String apellidoPaterno,
                                    @FormParam("apellidoMaterno") String apellidoMaterno,
                                    @FormParam("telefono") String telefono,
                                    @FormParam("direccion") String direccion,
                                    @FormParam("correoElectronico") String correoElectronico,
                                    @FormParam("fechaNacimiento") String fechaNacimiento,
                                    @FormParam("contrasenia") String contrasenia) {
        Mensaje mensaje = null;
        if (nombre != null && !nombre.isEmpty()) {
            mensaje = ClientesDAO.registrarCliente(nombre, apellidoPaterno, apellidoMaterno, telefono,direccion,
                    correoElectronico, fechaNacimiento, contrasenia);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }

    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarCliente(@FormParam("idCliente") int idCliente,
                                 @FormParam("nombre") String nombre,
                                 @FormParam("apellidoPaterno") String apellidoPaterno,
                                 @FormParam("apellidoMaterno") String apellidoMaterno,
                                 @FormParam("telefono") String telefono,
                                 @FormParam("direccion") String direccion,
                                 @FormParam("fechaNacimiento") String fechaNacimiento,
                                 @FormParam("contrasenia") String contrasenia) {
        Mensaje mensaje = null;
        if (idCliente > 0) {
            mensaje = ClientesDAO.editarCliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, telefono,
                    direccion, fechaNacimiento, contrasenia);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }

    @DELETE
    @Path("eliminar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarCliente(@FormParam("idCliente") int idCliente) {
        Mensaje mensaje = null;
        if (idCliente > 0) {
            mensaje = ClientesDAO.eliminarCliente(idCliente);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }
}

