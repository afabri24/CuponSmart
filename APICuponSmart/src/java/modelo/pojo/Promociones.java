/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo;

import java.util.Date;

/**
 *
 * @author ferna
 */
public class Promociones {
    private int idPromocion;
    private String nombre;
    private String descripcion;
    private byte[] imagen; // Se utiliza byte[] para representar datos binarios como imágenes
    private Date fechaInicio;
    private Date fechaTermino;
    private String restricciones;
    private int tipoPromocion;
    private float valorPromocion;
    private String categoria;
    private Integer cuponesMaximos; // Se utiliza Integer para permitir nulos
    private String codigoPromocion;
    private int estatus;
    private int idEmpresa;
    private int idSucursal;

    public Promociones() {
    }

    public Promociones(int idPromocion, String nombre, String descripcion, byte[] imagen, Date fechaInicio, Date fechaTermino, String restricciones, int tipoPromocion, float valorPromocion, String categoria, Integer cuponesMaximos, String codigoPromocion, int estatus, int idEmpresa, int idSucursal) {
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
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
