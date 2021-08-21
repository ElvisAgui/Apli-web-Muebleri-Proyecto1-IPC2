/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoipc.mimuebleria;

import java.io.IOException;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elvis_agui
 */
@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class Inicio extends HttpServlet {

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
        String nombre = request.getParameter("txtUsuario");
        String contras = request.getParameter("contra");
        LogeoEmpleado secion = new LogeoEmpleado();

        switch(secion.rango(nombre, contras)){
            case 1:
                response.sendRedirect("area-fabrica.jsp");
                break;
            default:
                response.sendRedirect("Logueo.jsp");
                break;
        }
    }

}
