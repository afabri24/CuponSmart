package ws;
import java.util.List;
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
import modelo.SucursalesDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.Sucursales;



@Path("sucursales")
public class SucursalesWS {
    @Context
    private UriInfo context;

    public SucursalesWS() {
    }

    @POST
    @Path("obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursales> obtenerSucursales() {
        List<Sucursales> sucursales = null;
        sucursales = SucursalesDAO.obtenerSucursales();
        if (sucursales == null) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }
        return sucursales;
    }

    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registroSucursal(@FormParam("nombre") String nombre,
                                    @FormParam("telefono") String telefono,
                                    @FormParam("latitud") float latitud,
                                    @FormParam("longitud") float longitud,
                                    @FormParam("direccion") String direccion,
                                    @FormParam("codigoPostal") String codigoPostal,
                                    @FormParam("ciudad") String ciudad,
                                    @FormParam("nombreEncargado") String nombreEncargado,
                                    @FormParam("idEmpresa") int idEmpresa) {
        Mensaje mensaje = null;
        if (nombre != null && !nombre.isEmpty()) {
            mensaje = SucursalesDAO.registrarSucursal(nombre, telefono, latitud, longitud, nombreEncargado, idEmpresa,direccion,codigoPostal,ciudad);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }

    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarSucursal(@FormParam("idSucursal") Integer idSucursal,
                                  @FormParam("nombre") String nombre,
                                  @FormParam("telefono") String telefono,
                                  @FormParam("latitud") float latitud,
                                  @FormParam("longitud") float longitud,
                                  @FormParam("direccion") String direccion,
                                  @FormParam("codigoPostal") String codigoPostal,
                                  @FormParam("ciudad") String ciudad,
                                  @FormParam("nombreEncargado") String nombreEncargado) {
        Mensaje mensaje = null;
        if (idSucursal != null) {
            mensaje = SucursalesDAO.editarSucursal(idSucursal, nombre, telefono, latitud, longitud, nombreEncargado,direccion,codigoPostal,ciudad);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }

    @DELETE
    @Path("eliminar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarSucursal(@FormParam("idSucursal") Integer idSucursal) {
        Mensaje mensaje = null;
        if (idSucursal != null) {
            mensaje = SucursalesDAO.eliminarSucursal(idSucursal);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return mensaje;
    }
    
}
