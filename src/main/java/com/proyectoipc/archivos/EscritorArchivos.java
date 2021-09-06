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
                }else{
                    linea.println(tituloV);
                }
                for (Venta venta : lista) {
                    linea.print(venta.getCorrelativo() + ",");
                    linea.print(venta.getFecha() + ",");
                    linea.print(venta.getMueble_ensamblado() + ",");
                    linea.print(venta.getNombreMueble() + ",");
                    linea.print(venta.getPrecioV() + ",");
                    if (esDv) {
                        linea.print(venta.getCliente()+ ",");
                        linea.print(venta.getFechaD() + ",");
                        linea.print(venta.getPerdida() + ",");
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

    public static int id() {
        return (int) (Math.random() * 999);

    }
}
