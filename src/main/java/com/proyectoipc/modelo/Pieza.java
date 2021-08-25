
package com.proyectoipc.modelo;

/**
 *
 * @author elvis_agui
 */
public class Pieza {
    private String nombre;
    private double Costo;
    private int cantidad;
    
    
    public Pieza(){
        
    }

    public Pieza(String nombre, double Costo, int cantidad) {
        this.nombre = nombre;
        this.Costo = Costo;
        this.cantidad = cantidad;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double Costo) {
        this.Costo = Math.round(Costo*100.0)/100.0;
        
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
