/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo;

/**
 *
 * @author ferna
 */
public class Cupones {

    private int idCupon;
    private int idPromocion;
    private String codigo;
    private String estatus;
    private int idCliente;

    public Cupones(int idCupon, int idPromocion, String codigo, String estatus, int idCliente) {
        this.idCupon = idCupon;
        this.idPromocion = idPromocion;
        this.codigo = codigo;
        this.estatus = estatus;
        this.idCliente = idCliente;
    }

    public Cupones() {
    }

    public int getIdCupon() {
        return idCupon;
    }

    public void setIdCupon(int idCupon) {
        this.idCupon = idCupon;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    
    
}
