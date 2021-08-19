
package com.proyectoipc.conexionSQL;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author elvis_agui
 */
public class Conexion {
    public static final String URL= "jdbc:mysql://localhost:3306/Muebleria";
    public static final String USER = "elvis-admin";
    public static final String PASS= "4056ELVIS";
    public static Connection conexion;
    
    public static Connection getConexion(){
        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
            return conexion;
        } catch (Exception e) {
            return null;
        }
        
    }
    
    
}
    