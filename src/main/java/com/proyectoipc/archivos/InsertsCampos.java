package com.proyectoipc.archivos;

import com.proyectoipc.Entidades.Cliente;
import com.proyectoipc.conexionSQL.Conexion;
import com.proyectoipc.modelo.Pieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author elvis_agui
 */
public class InsertsCampos {

    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;

    public void insertarCliente(Cliente cliente) {
        try {
            String consulta = "INSERT INTO cliente(nit, nombre, direccion) VALUES (?,?,?)";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, cliente.getNit());
            query.setString(2, cliente.getNombre());
            query.setString(3, cliente.getDireccion());
            query.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en insertar cliente"+ ex.getSQLState());
        } finally {
            cierre();
        }

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
