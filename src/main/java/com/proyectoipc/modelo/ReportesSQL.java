package com.proyectoipc.modelo;

import com.proyectoipc.Entidades.Ensamble;
import com.proyectoipc.Entidades.Venta;
import com.proyectoipc.conexionSQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elvis_agui
 */
public class ReportesSQL {
//    SELECT * FROM ensamble WHERE Fecha BETWEEN '2018-04-21' AND '2019-04-21';
//    SELECT DISTINCT mueble FROM ensamble;

    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;
    private String fechaI;
    private String fechaF;

    public List listaVenta(List<String> listaC) {
        int cont=0;
        List<Venta> lista = new ArrayList<>();
        List<Venta> listaAux;
        try {
            while (cont<listaC.size()) { 
                listaAux = getVenta(listaC.get(cont));
                for (int i = 0; i < listaAux.size(); i++) {
                    lista.add(listaAux.get(i));
                }
            }
        } catch (SQLException ex) {
            System.out.println("error en en la lista correlativasdlf " + ex.getMessage());
        } finally {
            cierre();
        }

        return lista;
    }

    public List getVenta(String correl) throws SQLException {
        String consulta = "SELECT mueble_ensamblado, fecha FROM venta WHERE correlativo=?";
        List<Venta> lista = new ArrayList<>();
        conexion = Conexion.getConexion();
        query = conexion.prepareStatement(consulta);
        query.setString(1, correl);
        result = query.executeQuery();
        while (result.next()) {
            Venta vtn = new Venta();
            String nobre = nombreM(result.getString("mueble_ensamblado"));
//            vtn.setFecha(result.getDate("fecha"));
            vtn.setCorrelativo(correl);
            vtn.setMueble_ensamblado(nobre);
            vtn.setGanancia(precioV(nobre));
            lista.add(vtn);
        }
        return lista;
    }

    public String nombreM(String id) {
        String nombre = "";
        try {
            String consulta = "SELECT mueble FROM ensamble WHERE id=?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, id);
            result = query.executeQuery();
            while (result.next()) {
                nombre = result.getString("mueble");
            }
        } catch (SQLException ex) {
            System.out.println("error en muble " + ex.getMessage());
        } 
        return nombre;

    }

    public double precioV(String nombre) {
        double precio = 0;
        try {
            String consulta = "SELECT precio_venta FROM mueble WHERE nombre=?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, nombre);
            result = query.executeQuery();
            while (result.next()) {
                precio = result.getDouble("precio_venta");
            }

        } catch (SQLException ex) {
            System.out.println("error en buccar precioVenta " + ex.getMessage());
        } 

        return precio;
    }

    /**
     * Realiza un lista de correlativos dependiendo la fecha o sin fecha
     *
     * @return llista de correlativos
     */
    public ArrayList<String> obtenerCorrelativoVenta() {
        ArrayList<String> lista = new ArrayList<>();
        try {
            if (fechaF != null && fechaI != null) {
                String consulta = "SELECT DISTINCT correlativo FROM venta WHERE fecha BETWEEN ? AND ?";
                conexion = Conexion.getConexion();
                query = conexion.prepareStatement(consulta);
                query.setDate(1, Ensamble.getFechaSF(fechaI));
                query.setDate(2, Ensamble.getFechaSF(fechaF));
                result = query.executeQuery();
                while (result.next()) {
                    lista.add(result.getString("correlativo"));
                }
            } else {
                String consulta = "SELECT DISTINCT correlativo FROM venta";
                conexion = Conexion.getConexion();
                query = conexion.prepareStatement(consulta);
                result = query.executeQuery();
                while (result.next()) {
                    lista.add(result.getString("correlativo"));
                }
            }
        } catch (ParseException ex) {
            System.out.println("error en formate");
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            setFechaF(null);
            setFechaI(null);
        }
        return lista;

    }

    public String getFechaI() {
        return fechaI;
    }

    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }

    public String getFechaF() {
        return fechaF;
    }

    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }

    private void cierre() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar sql  db");
            }
        }
        if (result != null) {
            try {
                result.close();
            } catch (SQLException ex) {
                System.out.println("error al cerrar resul");
            }
        }
        if (query != null) {
            try {
                query.close();
            } catch (SQLException ex) {
                System.out.println("error al cerrar query");
            }
        }
    }

}
