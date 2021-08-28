/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoipc.mimuebleria;

import com.proyectoipc.Entidades.Ensamble;
import com.proyectoipc.modelo.ConsulDB;
import com.proyectoipc.modelo.CosultDBaux;
import com.proyectoipc.modelo.Pieza;
import com.proyectoipc.Entidades.Pieza_Muble;
import com.proyectoipc.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elvis_agui
 */
public class Controlador extends HttpServlet {

    LocalDate fecha;
    Pieza pieza = new Pieza();
    CosultDBaux dbAux = new CosultDBaux();
    ConsulDB consul = new ConsulDB();
    String nomPieza;
    String nomPi;
    String nombM;
    Pieza_Muble piMue = new Pieza_Muble();
    Usuario usuario;
    String nombreus;
    Ensamble mEnsambel = new Ensamble();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("fabrica")) {
            request.getRequestDispatcher("Area-Fabrica/Principal-Frabricacion.jsp").forward(request, response);
        } else if (menu.equals("Ventas")) {
            request.getRequestDispatcher("Area-Fabrica/Principal-Frabricacion.jsp").forward(request, response);

        } else if (menu.equals("Administracion")) {

            request.getRequestDispatcher("Area-Fabrica/Principal-Frabricacion.jsp").forward(request, response);

        } else if (menu.equals("Crear")) {
            switch (accion) {
                case "listar":
                    List lista = consul.listar(false, false);
                    List listAgotada = consul.listaAgotada();
                    request.setAttribute("piezas", lista);
                    request.setAttribute("piezasAgot", listAgotada);
                    break;
                case "Editar":
                    nomPieza = request.getParameter("nom");
                    Pieza pi = consul.buscarPieza(nomPieza);
                    request.setAttribute("piezaA", pi);
                    request.getRequestDispatcher("Controlador?menu=Crear&accion=listar").forward(request, response);

                    break;
                case "Actualizar":
                    pieza.setNombre(request.getParameter("nombreE"));
                    pieza.setCosto(Double.parseDouble(request.getParameter("costoE")));
                    pieza.setCantidad(Integer.parseInt(request.getParameter("cantidadE")));
                    consul.Actualizar(pieza, nomPieza);
                    request.getRequestDispatcher("Controlador?menu=Crear&accion=listar").forward(request, response);
                    break;
                case "Agregar":
                    pieza.setNombre(request.getParameter("nombreN"));
                    pieza.setCosto(Double.parseDouble(request.getParameter("costoN")));
                    pieza.setCantidad(Integer.parseInt(request.getParameter("cantidadN")));
                    consul.CrearPieza(pieza);
                    request.getRequestDispatcher("Controlador?menu=Crear&accion=listar").forward(request, response);
                    break;
                case "Eliminar":
                    nomPieza = request.getParameter("nom");
                    consul.eliminar(nomPieza);
                    request.getRequestDispatcher("Controlador?menu=Crear&accion=listar").forward(request, response);
                    break;
                default:
                    ///erorro
                    break;

            }
            request.getRequestDispatcher("Area-Fabrica/Crear-Pieza.jsp").forward(request, response);

        } else if (menu.equals("Ensamble")) {
            switch (accion) {
                case "ListarM":
                    List listAgotada = consul.listaAgotada();
                    request.setAttribute("piezasAgot", listAgotada);
                    List listM = dbAux.listarMueble(false);
                    List listMEn = dbAux.listarMueble(true);
                    List lista = consul.listar(false, false);
                    request.setAttribute("piezas", lista);
                    request.setAttribute("mueblesEnsam", listMEn);
                    request.setAttribute("muebles", listM);
                    request.setAttribute("usuario", nombreus);
                    break;
                case "AgregarP":
                    piMue.setNombreP(request.getParameter("nomPi"));
                    request.setAttribute("Mue_PI", piMue);
                    request.getRequestDispatcher("Controlador?menu=Ensamble&accion=ListarM").forward(request, response);
                    break;
                case "AgregarM":
                    piMue.setNombreM(request.getParameter("nomM"));
                    request.setAttribute("Mue_PI", piMue);
                    request.getRequestDispatcher("Controlador?menu=Ensamble&accion=ListarM").forward(request, response);
                    break;
                case "Confirmar":
                    int cant = Integer.parseInt(request.getParameter("cantidadP"));
                    piMue.setCantidad(cant);
                    dbAux.ensambePiesMueble(piMue);
                    piMue.setCantidad(0);
                    piMue.setNombreM(null);
                    piMue.setNombreP(null);
                    request.getRequestDispatcher("Controlador?menu=Ensamble&accion=ListarM").forward(request, response);
                    break;
                case "Refrescar":
                    dbAux.EnsamblarMueble();
                    request.getRequestDispatcher("Controlador?menu=Ensamble&accion=ListarM").forward(request, response);
                    break;
                case "mubleEnsamblado":
                    LocalDateTime localDate = LocalDateTime.now();
                    DateTimeFormatter ad = DateTimeFormatter.ofPattern("dd/MM/YYYY");
                    mEnsambel.setMueble(request.getParameter("nomEns"));
                    request.setAttribute("muebleEnsable", mEnsambel);
                    request.setAttribute("fecha", ad.format(localDate));
                    mEnsambel.setFecha(Ensamble.getFecha(ad.format(localDate)));
                    request.getRequestDispatcher("Controlador?menu=Ensamble&accion=ListarM").forward(request, response);
                    break;
                case "Registrar":
                    if (dbAux.existeUsuario(request.getParameter("ensamblador"))) {
                        mEnsambel.setEnsamblador(request.getParameter("ensamblador"));
                        mEnsambel.setId(Ensamble.id());
                        mEnsambel.setGanancia(dbAux.calcGanancia(mEnsambel.getMueble()));
                        mEnsambel.setEnSala(false);
                        dbAux.guardarEnsamble(mEnsambel);
                        request.getRequestDispatcher("Controlador?menu=Ensamble&accion=ListarM").forward(request, response);

                    }

                    request.getRequestDispatcher("Controlador?menu=Ensamble&accion=ListarM").forward(request, response);

                    break;
                default:
                    ///erorro
                    break;
            }
            request.getRequestDispatcher("Area-Fabrica/Ensamblar-Mueble.jsp").forward(request, response);
        } else if (menu.equals("Registro")) {
            switch (accion) {
                case "lisatSala":
                    List Ensamble = consul.listaParaVenta();
                    request.setAttribute("listaParaVenta", Ensamble);
                    break;
                case "resigstroSala":
                    consul.enSala("" + request.getParameter("ensala"));
                    request.getRequestDispatcher("Controlador?menu=Registro&accion=lisatSala").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Area-Fabrica/Sala-Venta.jsp").forward(request, response);

        } else if (menu.equals("infoPiezas")) {
            List lista;
            switch (accion) {
                case "listaP":
                    lista = consul.listar(false, true);
                    request.setAttribute("piezas", lista);
                    break;
                case "ordenAsen":
                    lista = consul.listar(true, true);
                    request.setAttribute("piezas", lista);
                    break;
                case "ordenDes":
                    lista = consul.listar(true, false);
                    request.setAttribute("piezas", lista);
                    break;
            }
            request.getRequestDispatcher("Area-Fabrica/Info-Piezas.jsp").forward(request, response);
        } else if (menu.equals("infoMueble")) {
            List lista;
            switch (accion) {
                case "listaM":
                   lista = consul.infoMCreados(false, true);
                   request.setAttribute("infoMue", lista);
                    break;
                case "ordenAsen":
                    lista = consul.infoMCreados(true, true);
                    request.setAttribute("infoMue", lista);
                    break;
                case "ordenDes":
                    lista = consul.infoMCreados(true, false);
                    request.setAttribute("infoMue", lista);
                    break;
            }
            request.getRequestDispatcher("Area-Fabrica/Info-Mueble.jsp").forward(request, response);

        } else {
            response.sendRedirect("sesion/index.jsp");

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
