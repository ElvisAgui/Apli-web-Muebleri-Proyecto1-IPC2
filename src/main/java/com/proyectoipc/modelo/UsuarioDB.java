package com.proyectoipc.modelo;

import com.proyectoipc.conexionSQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author elvis_agui
 */
public class UsuarioDB {
    PreparedStatement query = null;
    ResultSet result = null;
    Connection conexion = null;

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
        }
        return us;
    }
}
