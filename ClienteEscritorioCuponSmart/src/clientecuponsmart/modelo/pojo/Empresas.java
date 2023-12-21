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
public class Empresas {
    private int idEmpresa;
    private String nombre;
    private String nombreComercial;
    private byte[] logo;
    private String representanteLegal;
    private String email;
    private String telefono;
    private String paginaWeb;
    private String RFC;
    private int estatus;
    private int idDireccion;

    public Empresas(int idEmpresa, String nombre, String nombreComercial, byte[] logo, String representanteLegal, String email, String telefono, String paginaWeb, String RFC, int estatus, int idDireccion) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.nombreComercial = nombreComercial;
        this.logo = logo;
        this.representanteLegal = representanteLegal;
        this.email = email;
        this.telefono = telefono;
        this.paginaWeb = paginaWeb;
        this.RFC = RFC;
        this.estatus = estatus;
        this.idDireccion = idDireccion;
    }

    public Empresas() {
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    
    
}
