
package com.proyectoipc.modelo;

/**
 *
 * @author elvis_agui
 */
public class Usuario {
    private String contra;
    private String nombre;
    private int rol;
    private int activo;
    
    public Usuario(){
        
    }

    public Usuario(String contra, String nombre, int rol, int activo) {
        this.contra = contra;
        this.nombre = nombre;
        this.rol = rol;
        this.activo = activo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    
    
}
