
package com.proyectoipc.Entidades;

/**
 *
 * @author elvis_agui
 */
public class Pieza_Muble {

    private String nombreP;
    private String nombreM;
    private int cantidad;
    
    
    public Pieza_Muble(){
        
    }

    public Pieza_Muble(String nombreM, String nombreP, int cantidad) {
        this.nombreP = nombreP;
        this.nombreM = nombreM;
        this.cantidad = cantidad;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getNombreM() {
        return nombreM;
    }

    public void setNombreM(String nombreM) {
        this.nombreM = nombreM;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
