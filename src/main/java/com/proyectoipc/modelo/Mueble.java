
package com.proyectoipc.modelo;

/**
 *
 * @author elvis_agui
 */
public class Mueble {
    
    private String nombre;
    private double precioVenta;
    private double costo;
    
    public Mueble(){
        
    }

    public Mueble(String nombre, double precioVenta) {
        this.nombre = nombre;
        this.precioVenta = precioVenta;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = Math.round(precioVenta*100.0)/100.0;;
    }
    
    
    
}
