
package com.proyectoipc.Entidades;

/**
 *
 * @author elvis_agui
 */
public class Cliente {
    private String nit;
    private String nombre;
    private String Direccion;
    
    public Cliente(){
        
    }

    public Cliente(String nit, String nombre, String Direccion) {
        this.nit = nit;
        this.nombre = nombre;
        this.Direccion = Direccion;
    }
    

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }
    
    
}
