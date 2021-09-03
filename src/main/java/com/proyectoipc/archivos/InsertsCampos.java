package com.proyectoipc.archivos;

import com.proyectoipc.Entidades.Cliente;
import com.proyectoipc.conexionSQL.Conexion;
import com.proyectoipc.modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author elvis_agui
 */
public class InsertsCampos {

    private CosultDBaux aux = new CosultDBaux();
    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;

    /**
     * recibe un cliente y lo inserta en la base de datos
     *
     * @param cliente
     */
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
            LectorArchivio.errores.add("error en cliente " + cliente.getNit());
            System.out.println("Error en insertar cliente " + ex.getMessage());
        } finally {
            cierre();
        }

    }

    /**
     * recibe un usuario y lo registar en la base de datos
     *
     * @param usuario
     */
    public void insertUsuario(Usuario usuario) {
        try {
            String consulta = "INSERT INTO usuario(contraseña, nombre, rol, activo) VALUES (?,?,?,?)";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, usuario.getContra());
            query.setString(2, usuario.getNombre());
            query.setInt(3, usuario.getRol());
            query.setBoolean(4, usuario.getActivo());
            query.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en insertar usuario" + ex.getMessage());
        } finally {
            cierre();
        }
    }

    /**
     * actualiza las piezas aun no referenciadas
     *
     * @param piezaActualizar
     * @param nombre
     * @return
     */
    public void Actualizar(Pieza piezaActualizar, String nombre) {
        try {
            String consulta = "UPDATE pieza SET  cantidad=? WHERE nombre=?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDouble(1, piezaActualizar.getCosto());
            query.setString(2, nombre);
            query.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("No puedes Actualizar el nombre");
        } finally {
            cierre();
        }
    }

    public void insertMueble(Mueble mueble) {
        try {
            String consulta = "INSERT INTO mueble(nombre, costo, precio_venta) VALUES (?,?,?)";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, mueble.getNombre());
            query.setDouble(2, mueble.getPrecioVenta());
            query.setDouble(3, mueble.getCosto());
            query.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en insertar mueble " + ex.getMessage());
            LectorArchivio.errores.add("Ya esta registrado" + mueble.getNombre());
        } finally {
            cierre();
        }
    }

    /**
     * busca pieza sin importar nombre tenga mayusculas
     *
     * @param nombre
     * @return boolean
     */
    public boolean existPieza(String nombre, boolean esMueble) {
        String consulta = "";
        String consultaP = "SELECT nombre FROM pieza";
        String consultaM = "SELECT nombre FROM mueble";
        if (esMueble) {
            consulta = consultaM;
        } else {
            consulta = consultaP;
        }
        boolean existe = false;
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            result = query.executeQuery();
            while (result.next()) {
                if (result.getString("nombre").equalsIgnoreCase(nombre)) {
                    existe = true;
                }

            }
        } catch (SQLException ex) {
            System.out.println("error en bucarPieza o mueble existente");
        } finally {
            cierre();
        }
        return existe;
    }

    public boolean repitPiez(String nombre, double precio) {
        boolean repi = false;
        String consulta = "SELECT nombre FROM pieza WHERE costo=?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDouble(1, precio);
            result = query.executeQuery();
            while (result.next()) {
                if (nombre.equalsIgnoreCase(result.getString("nombre"))) {
                    repi = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("errepite");
        } finally {
            cierre();
        }

        return repi;
    }

    /**
     * cambia el nombre correcto que esta registrdo en la base de datos
     *
     * @param nombre
     * @return String
     */
    public String nombreCorrecto(String nombre, boolean esMueble) {
        String consulta = "";
        String consultaP = "SELECT nombre FROM pieza";
        String consultaM = "SELECT nombre FROM mueble";
        if (esMueble) {
            consulta = consultaM;
        } else {
            consulta = consultaP;
        }
        String nombreC = "";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            result = query.executeQuery();
            while (result.next()) {
                if (result.getString("nombre").equalsIgnoreCase(nombre)) {
                    nombreC = result.getString("nombre");
                }

            }
        } catch (SQLException ex) {
            System.out.println("error en bucarPieza o mueble existente");
        } finally {
            cierre();
        }
        return nombreC;
    }

    public String obtenerConstrasña(String nombre) {
        String consulta = "SELECT contraseña FROM usuario WHERE nombre=?";
        String contra = "";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, nombre);
            result = query.executeQuery();
            while (result.next()) {
                contra = result.getString("contraseña");

            }
        } catch (SQLException ex) {
            System.out.println("error en bucarPieza o mueble existente");
        } finally {
            cierre();
        }

        return contra;
    }

    public boolean yaTienePieza(String nombre) {
        boolean ya = false;
        ArrayList<Mueble> lista = (ArrayList<Mueble>) aux.listarMueble(true);
        for (Mueble mueble : lista) {
            if (mueble.getNombre().equalsIgnoreCase(nombre)) {
                ya = true;
                break;
            }
        }
        return ya;
    }

    public boolean repitPorNomPie(String nombre) {
        boolean repi = false;
        String consulta = "SELECT nombre FROM pieza";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            result = query.executeQuery();
            while (result.next()) {
                if (nombre.equalsIgnoreCase(result.getString("nombre"))) {
                    repi = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("errepite");
        } finally {
            cierre();
        }

        return repi;
    }

    public boolean repetidoUs(String nombre) {
        boolean existe = false;
        String consulta = "SELECT nombre FROM usuario WHERE nombre=?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, nombre);
            result = query.executeQuery();
            while (result.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            cierre();
        }

        return existe;
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
