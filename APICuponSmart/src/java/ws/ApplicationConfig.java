/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ferna
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(SucursalesWS.class);
        resources.add(ws.AutentificacionWS.class);
        resources.add(ws.DireccionesWS.class);
        resources.add(ws.EmpresasWS.class);
        resources.add(ws.PromocionesWS.class);
        resources.add(ws.UsuariosWS.class);
    }
    
}
