package com.proyectoipc.modelo;

import com.proyectoipc.Entidades.Cliente;
import com.proyectoipc.Entidades.Venta;
import com.proyectoipc.conexionSQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author elvis_agui
 */
public class VentaSQL {

    private CosultDBaux aux = new CosultDBaux();
    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;

    /**
     * verifica si el cliente existe
     *
     * @param nit
     * @return nombre cliente
     */
    public String existeCliente(String nit) {
        String nombre = "NO ESTA REGISTRADO";
        String consulta = "SELECT nombre FROM cliente WHERE nit=?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, nit);
            result = query.executeQuery();
            while (result.next()) {
                nombre = result.getString("nombre");
            }
        } catch (SQLException ex) {
            System.out.println("error en bucarPieza");
        } finally {
            cierre();
        }

        return nombre;
    }

    public Cliente clienteF(String nit) {
        Cliente cliente = new Cliente();
        String consulta = "SELECT * FROM cliente WHERE nit=?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, nit);
            result = query.executeQuery();
            while (result.next()) {
                cliente.setNombre(result.getString("nombre"));
                cliente.setNit(result.getString("nit"));
                cliente.setDireccion(result.getString("direccion"));
            }
        } catch (SQLException ex) {
            System.out.println("error en bucarPieza");
        } finally {
            cierre();
        }

        return cliente;
    }

    public boolean repetidoLista(String nombre, ArrayList<Venta> lista) {
        boolean rep = false;
        for (Venta venta : lista) {
            if (venta.getMueble_ensamblado().equals(nombre)) {
                rep = true;
                break;
            }
        }
        return rep;
    }

    public String existeEnsamble(String id) {
        String nombreMue = "no";
        String consulta = "SELECT mueble FROM ensamble WHERE id=?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, id);
            result = query.executeQuery();
            while (result.next()) {
                nombreMue = result.getString("mueble");
            }
        } catch (SQLException ex) {
            System.out.println("error en ensamble");
        } finally {
            cierre();
        }

        return nombreMue;
    }

    public double ganaciaV(String id) {
        double ganacia = 0;
        String consulta = "SELECT ganancia FROM ensamble WHERE id=?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, id);
            result = query.executeQuery();
            while (result.next()) {
                ganacia = result.getDouble("ganancia");
            }
        } catch (SQLException ex) {
            System.out.println("error en ensamble");
        } finally {
            cierre();
        }
        return ganacia;
    }

    public double precioV(String nombre) {
        double preci = 0;
        String consulta = "SELECT precio_venta FROM mueble WHERE nombre=?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, nombre);
            result = query.executeQuery();
            while (result.next()) {
                preci = result.getDouble("precio_venta");
            }
        } catch (SQLException ex) {
            System.out.println("error en ensamble");
        } finally {
            cierre();
        }
        return preci;
    }

    public static String nomFac() {
        String fac = "";
        int num = (int) (Math.random() * 9999);
        fac = num + "-A";
        return fac;
    }

    public void insertarVenta(Venta venta, String usuario) {
        String consulta = "INSERT INTO venta(mueble_ensamblado, cliente, vendedor, ganacia, fecha, correlativo) VALUES (?,?,?,?,?,?)";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, venta.getMueble_ensamblado());
            query.setString(2, venta.getCliente());
            query.setString(3, usuario);
            query.setDouble(4, venta.getGanancia());
            query.setDate(5, venta.getFecha());
            query.setString(6, venta.getCorrelativo());
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error en listar inser ventaE " + e.getMessage());
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
