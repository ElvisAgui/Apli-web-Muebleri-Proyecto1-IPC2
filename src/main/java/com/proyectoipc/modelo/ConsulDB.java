package com.proyectoipc.modelo;

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
public class ConsulDB {

    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;
    private int resul;

    public List listar() {
        String consulta = "SELECT * FROM pieza";
        List<Pieza> lista = new ArrayList<>();
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            result = query.executeQuery();
            while (result.next()) {
                Pieza pieza = new Pieza();
                pieza.setNombre(result.getString("nombre"));
                pieza.setCantidad(result.getInt("cantidad"));
                pieza.setCosto(result.getDouble("costo"));
                lista.add(pieza);
            }
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            cierre();
        }

        return lista;
    }

    public Pieza buscarPieza(String nombre) {
        Pieza econtrada = new Pieza();
        String consulta = "SELECT * FROM pieza WHERE nombre=?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, nombre);
            result = query.executeQuery();
            while (result.next()) {
                econtrada.setNombre(result.getString("nombre"));
                econtrada.setCantidad(result.getInt("cantidad"));
                econtrada.setCosto(result.getDouble("costo"));

            }
        } catch (SQLException ex) {
            System.out.println("error en bucarPieza");
        } finally {
            cierre();
        }

        return econtrada;
    }

    public List listaAgotada() {
        String consulta = "SELECT * FROM pieza WHERE cantidad <= 10";
        List<Pieza> lista = new ArrayList<>();
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            result = query.executeQuery();
            while (result.next()) {
                Pieza pieza = new Pieza();
                pieza.setNombre(result.getString("nombre"));
                pieza.setCantidad(result.getInt("cantidad"));
                pieza.setCosto(result.getDouble("costo"));
                pieza.toString();
                lista.add(pieza);
            }

        } catch (SQLException ex) {
            System.out.println("Error en listaAgotados");
        } finally {
            cierre();
        }
        return lista;
    }

    public int Actualizar(Pieza piezaActualizar, String nombre) {
        try {
            String consulta = "UPDATE pieza SET nombre=?, costo=?, cantidad=? WHERE nombre=?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, piezaActualizar.getNombre());
            query.setDouble(2, piezaActualizar.getCosto());
            query.setInt(3, piezaActualizar.getCantidad());
            query.setString(4, nombre);
            query.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en Actualizar pieza");
        } finally {
            cierre();
        }

        return resul;
    }
    
    public void eliminar(String nombre){
        String consulta = "DELETE FROM pieza WHERE nombre=?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, nombre);
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro en eliminar");
        }finally{
            cierre();
        }
    }

    public int CrearPieza(Pieza piezaCreada) {
        try {
            String consulta = "INSERT INTO pieza(nombre, costo, cantidad) VALUES (?,?,?)";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, piezaCreada.getNombre());
            query.setDouble(2, piezaCreada.getCosto());
            query.setInt(3, piezaCreada.getCantidad());
            query.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en pieza Creada");
        } finally {
            cierre();
        }
        return resul;
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

    public Usuario validar(String nombre, String contra) {
        Usuario us = new Usuario();
        String consulta = "SELECT * FROM usuario WHERE nombre = ? AND contraseña = ?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, nombre);
            query.setString(2, contra);
            result = query.executeQuery();
            while (result.next()) {
                us.setNombre(result.getString("nombre"));
                us.setContra(result.getString("contraseña"));
                us.setRol(result.getInt("rol"));
                us.setActivo(result.getInt("activo"));

            }
        } catch (SQLException e) {
            System.out.println("error validar");
        } finally {
            cierre();
        }
        return us;
    }
}
