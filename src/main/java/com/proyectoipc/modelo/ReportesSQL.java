package com.proyectoipc.modelo;

import com.proyectoipc.Entidades.Ensamble;
import com.proyectoipc.Entidades.Venta;
import com.proyectoipc.conexionSQL.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elvis_agui
 */
public class ReportesSQL {
//    SELECT * FROM ensamble WHERE Fecha BETWEEN '2018-04-21' AND '2019-04-21';
//    SELECT DISTINCT mueble FROM ensamble;

    private VentaSQL dbaux = new VentaSQL();
    private PreparedStatement query = null;
    private ResultSet result = null;
    private Connection conexion = null;
    private String fechaI;
    private String fechaF;

    /**
     * Realiza un lista de correlativos dependiendo la fecha o sin fecha
     *
     * @return llista de correlativos
     */
    public ArrayList<String> obtenerCorrelativoVenta(String fechaI, String fechaF, boolean Disct) {
        ArrayList<String> lista = new ArrayList<>();
        try {
            String consulta = "SELECT DISTINCT correlativo FROM venta WHERE fecha BETWEEN ? AND ?";
            if (!Disct) {
                consulta = "SELECT  correlativo FROM venta WHERE fecha BETWEEN ? AND ?";
            }
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDate(1, Ensamble.getFechaSF(fechaI));
            query.setDate(2, Ensamble.getFechaSF(fechaF));
            result = query.executeQuery();
            while (result.next()) {
                lista.add(result.getString("correlativo"));
            }

        } catch (ParseException ex) {
            String consulta1 = "SELECT DISTINCT correlativo FROM venta";
            if (!Disct) {
                consulta1 = "SELECT  correlativo FROM venta";
            }
            try {
                conexion = Conexion.getConexion();
                query = conexion.prepareStatement(consulta1);
                result = query.executeQuery();
                while (result.next()) {
                    lista.add(result.getString("correlativo"));
                }
            } catch (SQLException ex1) {
                System.out.println("fjlasdfjasld");
            }
            System.out.println("error en formate");
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            setFechaF(null);
            setFechaI(null);
            cierre();
        }
        return lista;

    }

    public ArrayList<String> obtenerIDevu(String fechaI, String fechaF) {
        ArrayList<String> lista = new ArrayList<>();
        try {
            String consulta = "SELECT DISTINCT mueble_vendido FROM devolucion WHERE fecha BETWEEN ? AND ?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDate(1, Ensamble.getFechaSF(fechaI));
            query.setDate(2, Ensamble.getFechaSF(fechaF));
            result = query.executeQuery();
            while (result.next()) {
                lista.add(result.getString("mueble_vendido"));
            }

        } catch (ParseException ex) {
            String consulta1 = "SELECT DISTINCT mueble_vendido FROM devolucion";
            try {
                conexion = Conexion.getConexion();
                query = conexion.prepareStatement(consulta1);
                result = query.executeQuery();
                while (result.next()) {
                    lista.add(result.getString("mueble_vendido"));
                }
            } catch (SQLException ex1) {
                System.out.println("fjlasdfjasld");
            }
            System.out.println("error en formate");
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            setFechaF(null);
            setFechaI(null);
            cierre();
        }
        return lista;

    }

    public Date fechaDv(String idM) {
        Date fecha = null;
        try {
            String consulta = "SELECT fecha FROM devolucion WHERE mueble_vendido=?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, idM);
            result = query.executeQuery();
            while (result.next()) {
                fecha = result.getDate("fecha");
            }

        } catch (SQLException ex) {
            System.out.println("error en fecha devolucion " + ex.getMessage());
        } finally {
            cierre();
        }

        return fecha;
    }

    public double perdidadDv(String idM) {
        double per = 0;
        try {
            String consulta = "SELECT pedida FROM devolucion WHERE mueble_vendido=?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, idM);
            result = query.executeQuery();
            while (result.next()) {
                per = result.getDouble("pedida");
            }

        } catch (SQLException ex) {
            System.out.println("error en fecha devolucion " + ex.getMessage());
        } finally {
            cierre();
        }

        return per;
    }

    public ArrayList<String> obtenerIDMueble(String fechaI, String fechaF, boolean Disct) {
        ArrayList<String> lista = new ArrayList<>();
        try {
            String consulta = "SELECT DISTINCT mueble_ensamblado FROM venta WHERE fecha BETWEEN ? AND ?";
            if (!Disct) {
                consulta = "SELECT  mueble_ensamblado FROM venta WHERE fecha BETWEEN ? AND ?";
            }
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDate(1, Ensamble.getFechaSF(fechaI));
            query.setDate(2, Ensamble.getFechaSF(fechaF));
            result = query.executeQuery();
            while (result.next()) {
                lista.add(result.getString("mueble_ensamblado"));
            }

        } catch (ParseException ex) {
            String consulta1 = "SELECT DISTINCT mueble_ensamblado FROM venta";
            if (!Disct) {
                consulta1 = "SELECT  mueble_ensamblado FROM venta";
            }
            try {
                conexion = Conexion.getConexion();
                query = conexion.prepareStatement(consulta1);
                result = query.executeQuery();
                while (result.next()) {
                    lista.add(result.getString("mueble_ensamblado"));
                }
            } catch (SQLException ex1) {
                System.out.println("fjlasdfjasld");
            }
            System.out.println("error en formate");
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            setFechaF(null);
            setFechaI(null);
            cierre();
        }
        return lista;

    }

    public Venta obtenerVenta(String correlativo) {
        Venta vtn = new Venta();
        try {
            String consulta = "SELECT * FROM venta WHERE mueble_ensamblado=?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, correlativo);
            result = query.executeQuery();
            while (result.next()) {
                vtn.setMueble_ensamblado(result.getString("mueble_ensamblado"));
                vtn.setCliente(result.getString("cliente"));
                vtn.setFecha(result.getDate("fecha"));
                vtn.setCorrelativo(result.getString("correlativo"));
                vtn.setNombreMueble(dbaux.existeEnsamble(vtn.getMueble_ensamblado()));
                vtn.setPrecioV(dbaux.precioV(vtn.getNombreMueble()));
                vtn.setVendedor(nombreV(result.getString("vendedor")));
            }

        } catch (SQLException ex) {
            System.out.println("error en obtener Venta " + ex.getMessage());
        } finally {
            cierre();
        }

        return vtn;
    }

    public List listVentaMejorVendedor(String contra) {
        List<Venta> listaV = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM venta WHERE vendedor=?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, contra);
            result = query.executeQuery();
            while (result.next()) {
                Venta vtn = new Venta();
                vtn.setMueble_ensamblado(result.getString("mueble_ensamblado"));
                vtn.setCliente(result.getString("cliente"));
                vtn.setFecha(result.getDate("fecha"));
                vtn.setCorrelativo(result.getString("correlativo"));
                vtn.setNombreMueble(dbaux.existeEnsamble(vtn.getMueble_ensamblado()));
                vtn.setPrecioV(dbaux.precioV(vtn.getNombreMueble()));
                vtn.setVendedor(nombreV(contra));
                listaV.add(vtn);
            }

        } catch (SQLException ex) {
            System.out.println("error en obtener lista Venta " + ex.getMessage());
        } finally {
            cierre();
        }
        return listaV;
    }

    public List listVentaMejorMueble(String mueble) {
        List<Venta> listaV = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM venta WHERE nombre_mueble=?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, mueble);
            result = query.executeQuery();
            while (result.next()) {
                Venta vtn = new Venta();
                vtn.setMueble_ensamblado(result.getString("mueble_ensamblado"));
                vtn.setCliente(result.getString("cliente"));
                vtn.setFecha(result.getDate("fecha"));
                vtn.setCorrelativo(result.getString("correlativo"));
                vtn.setNombreMueble(dbaux.existeEnsamble(vtn.getMueble_ensamblado()));
                vtn.setPrecioV(dbaux.precioV(vtn.getNombreMueble()));
                vtn.setVendedor(nombreV(result.getString("vendedor")));
                listaV.add(vtn);
            }

        } catch (SQLException ex) {
            System.out.println("error en obtener lista Venta " + ex.getMessage());
        } finally {
            cierre();
        }
        return listaV;
    }

    public String nombreV(String contra) {
        String nombre = "";
        try {
            String consulta = "SELECT nombre FROM usuario WHERE contraseña=?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setString(1, contra);
            result = query.executeQuery();
            while (result.next()) {
                nombre = result.getString("nombre");
            }

        } catch (SQLException ex) {
            System.out.println("error en obtener Venta " + ex.getMessage());
        } finally {
            cierre();
        }
        return nombre;
    }

    public String mejorVendedor(String fechaI, String fechaF) {
        String vendedor = "";
        try {
            String consulta = "SELECT vendedor, COUNT( vendedor ) AS total FROM venta WHERE  fecha BETWEEN ? AND ? GROUP BY vendedor ORDER BY total DESC";

            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDate(1, Ensamble.getFechaSF(fechaI));
            query.setDate(2, Ensamble.getFechaSF(fechaF));
            result = query.executeQuery();
            while (result.next()) {
                vendedor = result.getString("vendedor");
                break;
            }

        } catch (ParseException ex) {
            String consulta1 = "SELECT vendedor, COUNT( vendedor ) AS total FROM venta GROUP BY vendedor ORDER BY total DESC";
            try {
                conexion = Conexion.getConexion();
                query = conexion.prepareStatement(consulta1);
                result = query.executeQuery();
                while (result.next()) {
                    vendedor = result.getString("vendedor");
                    break;
                }
            } catch (SQLException ex1) {
                System.out.println("fjlasdfjasld");
            }
            System.out.println("error en formate");
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            setFechaF(null);
            setFechaI(null);
            cierre();
        }

        return vendedor;
    }

    public List<String> mejorVendedorG(String fechaI, String fechaF) {
        List<String> listaV = new ArrayList<>();
        try {
            String consulta = "SELECT vendedor, SUM(ganacia) AS ganancia, count(vendedor) AS total FROM venta WHERE  fecha BETWEEN ? AND ? GROUP BY vendedor ORDER BY total DESC";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDate(1, Ensamble.getFechaSF(fechaI));
            query.setDate(2, Ensamble.getFechaSF(fechaF));
            result = query.executeQuery();
            while (result.next()) {
                listaV.add(result.getString("vendedor"));
                listaV.add(result.getString("ganancia"));
                break;
            }

        } catch (ParseException ex) {
            String consulta1 = "SELECT vendedor, SUM(ganacia) AS ganancia, count(vendedor) AS total FROM venta GROUP BY vendedor ORDER BY total DESC;";
            try {
                conexion = Conexion.getConexion();
                query = conexion.prepareStatement(consulta1);
                result = query.executeQuery();
                while (result.next()) {
                    listaV.add(result.getString("vendedor"));
                    listaV.add(result.getString("ganancia"));
                    break;
                }
            } catch (SQLException ex1) {
                System.out.println("fjlasdfjasld");
            }
            System.out.println("error en formate");
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            setFechaF(null);
            setFechaI(null);
            cierre();
        }

        return listaV;
    }

    public String mejorMueble(String fechaI, String fechaF) {
        String vendedor = "";
        try {
            String consulta = "SELECT nombre_mueble, COUNT( nombre_mueble ) AS total FROM venta WHERE  fecha BETWEEN ? AND ? GROUP BY nombre_mueble ORDER BY total DESC";

            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDate(1, Ensamble.getFechaSF(fechaI));
            query.setDate(2, Ensamble.getFechaSF(fechaF));
            result = query.executeQuery();
            while (result.next()) {
                vendedor = result.getString("nombre_mueble");
                break;
            }

        } catch (ParseException ex) {
            String consulta1 = "SELECT nombre_mueble, COUNT( nombre_mueble ) AS total FROM venta GROUP BY nombre_mueble ORDER BY total DESC";
            try {
                conexion = Conexion.getConexion();
                query = conexion.prepareStatement(consulta1);
                result = query.executeQuery();
                while (result.next()) {
                    vendedor = result.getString("nombre_mueble");
                    break;
                }
            } catch (SQLException ex1) {
                System.out.println("fjlasdfjasld");
            }
            System.out.println("error en formate");
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            setFechaF(null);
            setFechaI(null);
            cierre();
        }

        return vendedor;
    }

    public String peorMueble(String fechaI, String fechaF) {
        String vendedor = "";
        try {
            String consulta = "SELECT nombre_mueble, COUNT( nombre_mueble ) AS total FROM venta WHERE  fecha BETWEEN ? AND ? GROUP BY nombre_mueble ORDER BY total ASC";

            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDate(1, Ensamble.getFechaSF(fechaI));
            query.setDate(2, Ensamble.getFechaSF(fechaF));
            result = query.executeQuery();
            while (result.next()) {
                vendedor = result.getString("nombre_mueble");
                break;
            }

        } catch (ParseException ex) {
            String consulta1 = "SELECT nombre_mueble, COUNT( nombre_mueble ) AS total FROM venta GROUP BY nombre_mueble ORDER BY total ASC";
            try {
                conexion = Conexion.getConexion();
                query = conexion.prepareStatement(consulta1);
                result = query.executeQuery();
                while (result.next()) {
                    vendedor = result.getString("nombre_mueble");
                    break;
                }
            } catch (SQLException ex1) {
                System.out.println("fjlasdfjasld");
            }
            System.out.println("error en formate");
        } catch (SQLException e) {
            System.out.println("error en listar priductos");
        } finally {
            setFechaF(null);
            setFechaI(null);
            cierre();
        }

        return vendedor;
    }

    public double obtenerTGanancia(String fechaI, String fechaF) {
//        SELECT SUM(ganacia) FROM venta WHERE fecha BETWEEN '2021-08-26' AND '2021-09-02';
        double ganancia = 0;
        try {
            String consulta = "SELECT SUM(ganacia) FROM venta WHERE fecha BETWEEN ? AND ?";
            conexion = Conexion.getConexion();
            query = conexion.prepareStatement(consulta);
            query.setDate(1, Ensamble.getFechaSF(fechaI));
            query.setDate(2, Ensamble.getFechaSF(fechaF));
            result = query.executeQuery();
            while (result.next()) {
                ganancia = result.getDouble("SUM(ganacia)");
            }

        } catch (SQLException ex) {
            System.out.println("suma ganancia con fecha " + ex.getMessage());
        } catch (ParseException ex) {
            try {
                String consulta1 = "SELECT SUM(ganacia) FROM venta";
                conexion = Conexion.getConexion();
                query = conexion.prepareStatement(consulta1);
                result = query.executeQuery();
                while (result.next()) {
                    ganancia = result.getDouble("SUM(ganacia)");
                }
            } catch (SQLException ex1) {
                System.out.println("error en sum ganancia");
            }
        } finally {
            cierre();
        }
        return ganancia;
    }

    public String getFechaI() {
        return fechaI;
    }

    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }

    public String getFechaF() {
        return fechaF;
    }

    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
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
