package com.proyectoipc.archivos;

import com.proyectoipc.Entidades.Venta;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author elvis_agui
 */
public class EscritorArchivos {

    File archivo;
    FileWriter escribir;
    PrintWriter linea;
    String nombreArchivo;
    
    /**
     * escribe en un archivo el el listado en elpat que rcibe
     * @param lista
     * @param path
     * @param esDv
     * @return 
     */
    public String escribirArchivo(ArrayList<Venta> lista, String path, boolean esDv) {
        nombreArchivo = path + "/reporteVenta" + EscritorArchivos.id() + ".csv";
        String tituloV = "REPORTE VENTA\nNO.Factura,Fecha,id Mueble,Nombre Mueble,Precio venta, NIT cliente";
        String tituloD = "REPORTE DEVOLUCION\nNO.Factura,Fecha,id Mueble,Nombre Mueble,Precio venta,NIT cliente,Fecha Devolucion,Perdida";
        if (esDv) {
            nombreArchivo = path + "/reporteDevolucion" + EscritorArchivos.id() + ".csv";
        }
        archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                if (esDv) {
                    linea.println(tituloD);
                } else {
                    linea.println(tituloV);
                }
                for (Venta venta : lista) {
                    linea.print(venta.getCorrelativo() + ",");
                    linea.print(venta.getFecha() + ",");
                    linea.print(venta.getMueble_ensamblado() + ",");
                    linea.print(venta.getNombreMueble() + ",");
                    linea.print(venta.getPrecioV() + ",");
                    if (esDv) {
                        linea.print(venta.getCliente() + ",");
                        linea.print(venta.getFechaD() + ",");
                        linea.print(venta.getPerdida());
                        linea.println("");
                    } else {
                        linea.print(venta.getCliente());
                        linea.println("");
                    }
                }
                linea.close();
            } catch (IOException ex) {
                System.out.println("erroe ne creear archivo");
            }
        } else {
            System.out.println("archivo ay existens");
        }
        return nombreArchivo;
    }

    /**
     * escribe el archivo de la ganacia en el listado, con path y su ganancia
     * @param lista
     * @param path
     * @param ganancia
     * @return 
     */
    public String EscArchivoGanancia(ArrayList<Venta> lista, String path, String ganancia) {
        nombreArchivo = path + "/reporteGanancia" + EscritorArchivos.id() + ".csv";
        String titulo = "REPORTE GANANCIA\nFecha venta,ID Mueble,Nombre Producto,Precio venta";
        archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(titulo);
                for (Venta venta : lista) {
                    linea.print(venta.getFecha() + ",");
                    linea.print(venta.getMueble_ensamblado() + ",");
                    linea.print(venta.getNombreMueble() + ",");
                    linea.print(venta.getVendedor() + ",");
                    linea.print(venta.getPrecioV());
                    linea.println("");
                }
                linea.println("Total ganancia,"+ganancia);
                linea.close();
            } catch (IOException ex) {
                System.out.println("erroe en crear archivo ganancia");
            }
        } else {
            System.out.println("archivo ya existens");
        }
        return nombreArchivo;
    }
    public String EscArchivoGananciaUS(ArrayList<Venta> lista, String path, String ganancia, String usuario) {
        nombreArchivo = path + "/reporteGanancia" + EscritorArchivos.id() + ".csv";
        String titulo = "REPORTE GANANCIA USUARIO\nFecha venta,ID Mueble,Nombre Producto,Precio venta, ganancia";
        archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println("Usuario---- "+ usuario);
                linea.println(titulo);
                for (Venta venta : lista) {
                    linea.print(venta.getFecha() + ",");
                    linea.print(venta.getMueble_ensamblado() + ",");
                    linea.print(venta.getNombreMueble() + ",");
                    linea.print(venta.getVendedor() + ",");
                    linea.print(venta.getPrecioV()+ ",");
                    linea.print(venta.getGanancia());
                    linea.println("");
                }
                linea.println("Total ganancia,"+ganancia);
                linea.close();
            } catch (IOException ex) {
                System.out.println("erroe en crear archivo ganancia");
            }
        } else {
            System.out.println("archivo ya existens");
        }
        return nombreArchivo;
    }
    
    public String EscArchivoUsuario(ArrayList<Venta> lista, String path, String usuario) {
        nombreArchivo = path + "/reporteUsuario" + EscritorArchivos.id() + ".csv";
        String titulo = "REPORTE USUARIO CON MAS VENTAS\nFecha venta,ID Mueble,Nombre Producto,Precio venta";
        archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(titulo);
                linea.println("USUAIOR--,"+usuario);
                for (Venta venta : lista) {
                    linea.print(venta.getFecha() + ",");
                    linea.print(venta.getMueble_ensamblado() + ",");
                    linea.print(venta.getNombreMueble() + ",");
                    linea.print(venta.getPrecioV());
                    linea.println("");
                }
                linea.close();
            } catch (IOException ex) {
                System.out.println("erroe en crear archivo ganancia");
            }
        } else {
            System.out.println("archivo ya existens");
        }
        return nombreArchivo;
    }
    public String EscArchivoMuebleM(ArrayList<Venta> lista, String path, String usuario) {
        nombreArchivo = path + "/reporteMueble" + EscritorArchivos.id() + ".csv";
        String titulo = "REPORTE MUEBLE MAS VENDIDO\nFecha venta,ID Mueble,Nombre Producto,Precio venta";
        archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(titulo);
                linea.println("MUEBLE--,"+usuario);
                for (Venta venta : lista) {
                    linea.print(venta.getVendedor() + ",");
                    linea.print(venta.getFecha() + ",");
                    linea.print(venta.getMueble_ensamblado() + ",");
                    linea.print(venta.getNombreMueble() + ",");
                    linea.print(venta.getPrecioV());
                    linea.println("");
                }
                linea.close();
            } catch (IOException ex) {
                System.out.println("erroe en crear archivo ganancia");
            }
        } else {
            System.out.println("archivo ya existens");
        }
        return nombreArchivo;
    }
    public String EscArchivoMuebleMenos(ArrayList<Venta> lista, String path, String usuario) {
        nombreArchivo = path + "/reporteMenos" + EscritorArchivos.id() + ".csv";
        String titulo = "REPORTE Menos Vendido\nFecha venta,ID Mueble,Nombre Producto,Precio venta";
        archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(titulo);
                linea.println("MUEBLE--,"+usuario);
                for (Venta venta : lista) {
                    linea.print(venta.getVendedor() + ",");
                    linea.print(venta.getFecha() + ",");
                    linea.print(venta.getMueble_ensamblado() + ",");
                    linea.print(venta.getNombreMueble() + ",");
                    linea.print(venta.getPrecioV());
                    linea.println("");
                }
                linea.close();
            } catch (IOException ex) {
                System.out.println("erroe en crear archivo ganancia");
            }
        } else {
            System.out.println("archivo ya existens");
        }
        return nombreArchivo;
    }

    public static int id() {
        return (int) (Math.random() * 999);

    }
}
