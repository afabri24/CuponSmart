/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientecuponsmart.modelo.pojo;

/**
 *
 * @author afabri24
 */
public class RespuestaLogin {
    private Boolean error;
    private String mensaje;
    private Usuarios Usuario;

    public RespuestaLogin(Boolean error, String mensaje, Usuarios Usuario) {
        this.error = error;
        this.mensaje = mensaje;
        this.Usuario = Usuario;
    }

    public RespuestaLogin() {
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

    public Usuarios getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuarios Usuario) {
        this.Usuario = Usuario;
    }
    
    
}
