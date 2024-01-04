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
public class Promociones {
    private int idPromocion;
    private String nombre;
    private String descripcion;
    private byte[] imagen;
    private String fechaInicio;
    private String fechaTermino;
    private String restricciones;
    private int tipoPromocion;
    private float valorPromocion;
    private String categoria;
    private Integer cuponesMaximos;
    private String codigoPromocion;
    private int estatus;
    private int idEmpresa;
    private int idSucursal;

    public Promociones(int idPromocion, String nombre, String descripcion, byte[] imagen, String fechaInicio, String fechaTermino, String restricciones, int tipoPromocion, float valorPromocion, String categoria, Integer cuponesMaximos, String codigoPromocion, int estatus, int idEmpresa, int idSucursal) {
        this.idPromocion = idPromocion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.restricciones = restricciones;
        this.tipoPromocion = tipoPromocion;
        this.valorPromocion = valorPromocion;
        this.categoria = categoria;
        this.cuponesMaximos = cuponesMaximos;
        this.codigoPromocion = codigoPromocion;
        this.estatus = estatus;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
    }

    public Promociones() {
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }

    public int getTipoPromocion() {
        return tipoPromocion;
    }

    public void setTipoPromocion(int tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }

    public float getValorPromocion() {
        return valorPromocion;
    }

    public void setValorPromocion(float valorPromocion) {
        this.valorPromocion = valorPromocion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getCuponesMaximos() {
        return cuponesMaximos;
    }

    public void setCuponesMaximos(Integer cuponesMaximos) {
        this.cuponesMaximos = cuponesMaximos;
    }

    public String getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(String codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    
    
}
