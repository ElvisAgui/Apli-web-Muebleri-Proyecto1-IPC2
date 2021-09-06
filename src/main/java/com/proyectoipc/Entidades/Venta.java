package com.proyectoipc.Entidades;

import java.sql.Date;

/**
 *
 * @author elvis_agui
 */
public class Venta {

    private String mueble_ensamblado;
    private String cliente;
    private String vendedor;
    private double ganancia;
    private Date fecha;
    private String correlativo;
    private String nombreMueble;
    private double precioV;
    private Date fechaD;
    private double perdida;

    
    public Venta(){
        
    }
    
    public Venta(String mueble_ensamblado, String cliente, String vendedor, double ganancia, Date fecha, String correlativo) {
        this.mueble_ensamblado = mueble_ensamblado;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.ganancia = ganancia;
        this.fecha = fecha;
        this.correlativo = correlativo;
    }

    public String getMueble_ensamblado() {
        return mueble_ensamblado;
    }

    public void setMueble_ensamblado(String mueble_ensamblado) {
        this.mueble_ensamblado = mueble_ensamblado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }

    public double getPrecioV() {
        return precioV;
    }

    public void setPrecioV(double precioV) {
        this.precioV = precioV;
    }

    public Date getFechaD() {
        return fechaD;
    }

    public void setFechaD(Date fechaD) {
        this.fechaD = fechaD;
    }

    public double getPerdida() {
        return perdida;
    }

    public void setPerdida(double perdida) {
        this.perdida = perdida;
    }
    

    @Override
    public String toString() {
        return "Venta{" + "mueble_ensamblado=" + mueble_ensamblado + ", cliente=" + cliente + ", vendedor=" + vendedor + ", ganancia=" + ganancia + ", fecha=" + fecha + ", correlativo=" + correlativo + ", nombreMueble=" + nombreMueble + ", precioV=" + precioV + '}';
    }
    
    
    
    

    
}
