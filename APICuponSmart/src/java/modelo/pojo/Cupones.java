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
    private Integer numeroCuponesDisponibles;

    public Cupones() {
    }

    public Cupones(int idCupon, int idPromocion, Integer numeroCuponesDisponibles) {
        this.idCupon = idCupon;
        this.idPromocion = idPromocion;
        this.numeroCuponesDisponibles = numeroCuponesDisponibles;
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

    public Integer getNumeroCuponesDisponibles() {
        return numeroCuponesDisponibles;
    }

    public void setNumeroCuponesDisponibles(Integer numeroCuponesDisponibles) {
        this.numeroCuponesDisponibles = numeroCuponesDisponibles;
    }
    
    
}
