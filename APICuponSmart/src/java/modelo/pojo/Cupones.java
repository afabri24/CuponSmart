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
public class Cupones {
    private int idCupon;
    private int numeroCuponesDisponibles;
    private int idPromocion;

    public Cupones(int idCupon, int numeroCuponesDisponibles, int idPromocion) {
        this.idCupon = idCupon;
        this.numeroCuponesDisponibles = numeroCuponesDisponibles;
        this.idPromocion = idPromocion;
    }

    public Cupones() {
    }

    public int getIdCupon() {
        return idCupon;
    }

    public void setIdCupon(int idCupon) {
        this.idCupon = idCupon;
    }

    public int getNumeroCuponesDisponibles() {
        return numeroCuponesDisponibles;
    }

    public void setNumeroCuponesDisponibles(int numeroCuponesDisponibles) {
        this.numeroCuponesDisponibles = numeroCuponesDisponibles;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }
    
    
}
