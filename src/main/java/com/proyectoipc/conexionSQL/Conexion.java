package com.proyectoipc.conexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;
/**
 *
 * @author elvis_agui
 */
public class Conexion {

    public String URL = "jdbc:mysql://localhost:3306/Muebleria";
    public String USER = "elvis-admin";
    public String PASS = "4056ELVIS";
    public Connection conexion;

    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            System.err.println("erro en conexion sql");
        } catch (ClassNotFoundException ex) {
            System.err.println("erro en conexion calas name");
            
        }
    }

    public Connection getConexion() {
        return conexion;

    }
    
}
