/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo;

/**
 *
 * @author afabri24
 */
public class RespuestaLoginEscritorio {
    private Boolean error;
    private String mensaje;
    private Usuarios usuariosSesion;

    public RespuestaLoginEscritorio(Boolean error, String mensaje, Usuarios usuariosSesion) {
        this.error = error;
        this.mensaje = mensaje;
        this.usuariosSesion = usuariosSesion;
    }

    public RespuestaLoginEscritorio() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuarios getUsuariosSesion() {
        return usuariosSesion;
    }

    public void setUsuariosSesion(Usuarios usuariosSesion) {
        this.usuariosSesion = usuariosSesion;
    }
    
    
}
