package com.proyectoipc.modelo;

import com.proyectoipc.Entidades.Pieza_Muble;
import com.proyectoipc.conexionSQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elvis_agui
 */
public class CosultDBaux {

    private ConsulDB pieza;
    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;

    public void ensambePiesMueble(Pieza_Muble insert) {
        String consulta = "INSERT INTO pieza_mueble(nombre_mueble, nombre_pieza, cantidad) VALUES (?,?,?)";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, insert.getNombreM());
            query.setString(2, insert.getNombreP());
            query.setInt(3, insert.getCantidad());
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error en listar inser PIeza:MUeble");
        } finally {
            cierre();
        }
    }

    public void EnsamblarMueble() {
        double suma = 0;
        String nombPieza;
        int cantidad;
        try {
            List<Mueble> lista;
            List<Pieza_Muble> listaMuP;
            lista = listarMueble();
            listaMuP = mueble_Pieza();
            for (Mueble mueble : lista) {
                for (Pieza_Muble pieza_Muble : listaMuP) {
                    if (mueble.getNombre().equalsIgnoreCase(pieza_Muble.getNombreM())) {
                        cantidad = pieza_Muble.getCantidad();
                        nombPieza = pieza_Muble.getNombreP();
                        suma = +(cantidad * precioP(nombPieza));
                    }
                }
                if (mueble.getCosto() == 0.00) {
                    mueble.setCosto(suma);
                }
            }
        } catch (Exception e) {
            System.out.println("actualizar costo Mueble");
        } 
    }

    private double precioP(String nomPi) {
        double precio = 0;
        String consulta = "SELECT costo FROM pieza WHERE nombre=?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, nomPi);
            result = query.executeQuery();
            while (result.next()) {
                precio = result.getDouble("costo");
            }
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            cierre();
        }
        return precio;
    }

    public List mueble_Pieza() {
        List<Pieza_Muble> lista = new ArrayList<>();
        String consulta = "SELECT * FROM pieza_mueble";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            result = query.executeQuery();
            while (result.next()) {
                Pieza_Muble pi_Mu = new Pieza_Muble();
                pi_Mu.setNombreM(result.getString("nombre_mueble"));
                pi_Mu.setNombreP(result.getString("nombre_pieza"));
                pi_Mu.setCantidad(result.getInt("cantidad"));
                lista.add(pi_Mu);
            }
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            cierre();
        }
        return lista;
    }

    public List listarMueble() {
        String consulta = "SELECT * FROM mueble WHERE costo=0.00";
        List<Mueble> lista = new ArrayList<>();
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            result = query.executeQuery();
            while (result.next()) {
                Mueble mueble = new Mueble();
                mueble.setNombre(result.getString("nombre"));
                mueble.setPrecioVenta(result.getDouble("precio_venta"));
                mueble.setCosto(result.getDouble("costo"));
                lista.add(mueble);
            }
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            cierre();
        }

        return lista;
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
