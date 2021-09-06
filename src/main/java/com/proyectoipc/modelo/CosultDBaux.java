package com.proyectoipc.modelo;

import com.proyectoipc.Entidades.Ensamble;
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

    /**
     *actualiza el costo de los muebles con base a las piezas que insertaron
     */
    public void EnsamblarMueble() {
        double suma = 0;
        String nombPieza;
        int cantidad;
        try {
            List<Mueble> lista;
            List<Pieza_Muble> listaMuP;
            lista = listarMueble(false);
            listaMuP = mueble_Pieza();
            for (Mueble mueble : lista) {
                for (Pieza_Muble pieza_Muble : listaMuP) {
                    if (mueble.getNombre().equalsIgnoreCase(pieza_Muble.getNombreM())) {
                        cantidad = pieza_Muble.getCantidad();
                        nombPieza = pieza_Muble.getNombreP();
                        suma = +(cantidad * precioP(nombPieza));
                    }
                }
                if (mueble.getCosto() == 0) {
                    mueble.setCosto(suma);
                    actualizarMueble(mueble);
                }
            }
        } catch (Exception e) {
            System.out.println("actualizar costo Mueble");
        }
    }
    
    /**
     * actualiza los campos del mueble ya ensamblado
     * @param mueble 
     */
    public void actualizarMueble(Mueble mueble) {
        try {
            String consulta = "UPDATE mueble SET costo=? WHERE nombre=?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDouble(1, mueble.getCosto());
            query.setString(2, mueble.getNombre());
            query.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en Actualizar pieza");
        } finally {
            cierre();
        }

    }
    
    /**
     * debulve un precio del que necesitamos
     * @param nomPi
     * @return 
     */
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
    /**
     * lista de muebles que hay que mostrar
     * @return 
     */
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
    
    /**
     * verifica si el usuarios existe mediante su contraseña
     * @param nombre
     * @return 
     */
    public boolean existeUsuario(String nombre) {
        boolean existe = false;
        String consulta = "SELECT contraseña FROM usuario WHERE contraseña=?";
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

    public List listarMueble(boolean conCosto) {
        String consultaCosto = "SELECT * FROM mueble WHERE costo>0.00";
        String consulta = "SELECT * FROM mueble WHERE costo=0.00";
        if (conCosto) {
            consulta = consultaCosto;
        }
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

  

    public void guardarEnsamble(Ensamble ensamble) {
        String consulta = "INSERT INTO ensamble(id, mueble, ensamblador, Fecha, ganancia, en_sala) VALUES (?,?,?,?,?,?)";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, "" + ensamble.getId());
            query.setString(2, ensamble.getMueble());
            query.setString(3, ensamble.getEnsamblador());
            query.setDate(4, ensamble.getFecha());
            query.setDouble(5, ensamble.getGanancia());
            query.setBoolean(6, ensamble.getEnSala());
            query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error en listar inser ENSAMBLE " + e.getMessage());
        } finally {
            cierre();
        }
    }

    public double calcGanancia(String nombreMu) {
        double ganancia = 0;
        String consulta = "SELECT * FROM mueble WHERE nombre=?";
        try {
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, nombreMu);
            result = query.executeQuery();
            while (result.next()) {
                ganancia = (result.getDouble("precio_venta") - result.getDouble("costo"));
            }
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            cierre();
        }
        return ganancia;

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
