package com.proyectoipc.Entidades;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author elvis_agui
 */
public class Ensamble {

    private int id;
    private String mueble;
    private String ensamblador;
    private Date fecha;
    private double ganancia;
    private boolean enSala;
    private String enVenta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMueble() {
        return mueble;
    }

    public void setMueble(String mueble) {
        this.mueble = mueble;
    }

    public String getEnsamblador() {
        return ensamblador;
    }

    public void setEnsamblador(String ensamblador) {
        this.ensamblador = ensamblador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public boolean getEnSala() {
        return enSala;
    }

    public void setEnSala(boolean enSala) {
        this.enSala = enSala;
    }

    public static int id() {
        return (int) (Math.random() * 9999);

    }

    public String getEnVenta() {
        if (enSala) {
            enVenta = "SI";
        } else {
            enVenta = "NO";
        }
        return enVenta;
    }

    public static Date getFecha(String localDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        java.util.Date nFecha = formato.parse(localDate);
        fecha = new java.sql.Date(nFecha.getTime());

        return fecha;
    }
    public static Date getFechaSF(String localDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        java.util.Date nFecha = formato.parse(localDate);
        fecha = new java.sql.Date(nFecha.getTime());

        return fecha;
    }

}
