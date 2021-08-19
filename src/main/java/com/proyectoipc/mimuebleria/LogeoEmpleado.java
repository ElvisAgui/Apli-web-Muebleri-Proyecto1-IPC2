package com.proyectoipc.mimuebleria;

import com.proyectoipc.conexionSQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elvis_agui
 */
public class LogeoEmpleado {

    private Connection conexion = null;
    PreparedStatement query;
    String consulta = "SELECT rol FROM usuario WHERE nombre = ? AND contraseña = ?";

    public int rango(String nombre, String password) {
        int rango = 0;

        conexion = Conexion.getConexion();
        try {
            query = conexion.prepareStatement(consulta);
            query.setString(1, nombre);
            query.setString(2, password);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                rango = result.getInt(1);
            }
            if (conexion != null) {
                conexion = null;
            }
            return rango;

        } catch (SQLException ex) {
            return rango;
        }

    }
}
