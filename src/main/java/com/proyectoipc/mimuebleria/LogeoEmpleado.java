package com.proyectoipc.mimuebleria;

import com.proyectoipc.conexionSQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author elvis_agui
 */
public class LogeoEmpleado extends Conexion {

    private Connection conexion = null;

    public int rango(String nombre, String password) {
        PreparedStatement query = null;
        ResultSet result = null;
        int rango = 0;
        String consulta = "SELECT rol FROM usuario WHERE nombre = ? AND contraseña = ?";
        try {
            conexion = getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, nombre);
            query.setString(2, password);
            result = query.executeQuery();
            while (result.next()) {
                rango = result.getInt(1);
            }

            return rango;

        } catch (SQLException ex) {
            System.out.println("errror en loego empleado");
            return rango;
        } finally {
            if (conexion != null) {
                conexion = null;
            }
            if (query != null) {
                try {
                    query.close();
                } catch (SQLException ex) {
                    System.out.println("----");
                }
            }
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    System.out.println("---");
                }
            }
        }

    }

    public boolean existe(String nombre, String contraseña) {
        PreparedStatement query = null;
        ResultSet result = null;
        boolean existe = false;
        String conSQL = "SELECT rol FROM usuario WHERE nombre = ? AND contraseña = ?";
        try {
            if (getConexion() != null) {
                System.out.println("entre a la conexion");
                query = getConexion().prepareStatement(conSQL);
                query.setString(1, nombre);
                query.setString(2, contraseña);
                result = query.executeQuery();
                if (result.absolute(1)) {
                    System.out.println("si existe");
                    existe = true;
                }
                getConexion().close();
                if (query != null) {
                    query.close();
                }
                if (result != null) {
                    result.close();
                }
            } else {
                System.err.println("error en logeo ");

            }

        } catch (SQLException e) {
            System.err.println("ERROR EN SENTANCIA SQL " + e.getMessage());
        }

        return existe;
    }

}
