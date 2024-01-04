/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientecuponsmart.modelo.pojo;

/**
 *
 * @author ferna
 */
public class Promociones {
    private int idPromocion;
    private String nombre;
    private String descripcion;
    private byte[] imagen;
    private String fechaInicio;
    private String fechaTermino;
    private String restricciones;
    private String tipo;
    private float valor;
    private String categoria;
    private Integer cuponesMaximos;
    private String codigo;
    private String estatus;
    private int idEmpresa;
    private String imagenBase64;

    public Promociones(int idPromocion, String nombre, String descripcion, byte[] imagen, String fechaInicio, String fechaTermino, String restricciones, String tipo, float valor, String categoria, Integer cuponesMaximos, String codigo, String estatus, int idEmpresa, String imagenBase64) {
        this.idPromocion = idPromocion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.restricciones = restricciones;
        this.tipo = tipo;
        this.valor = valor;
        this.categoria = categoria;
        this.cuponesMaximos = cuponesMaximos;
        this.codigo = codigo;
        this.estatus = estatus;
        this.idEmpresa = idEmpresa;
        this.imagenBase64 = imagenBase64;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getImagenBase64() {
        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }

   
    
}
