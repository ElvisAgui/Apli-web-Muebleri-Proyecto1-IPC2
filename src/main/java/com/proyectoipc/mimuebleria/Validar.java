/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoipc.mimuebleria;

import com.proyectoipc.modelo.Usuario;
import com.proyectoipc.modelo.UsuarioDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elvis_agui
 */
public class Validar extends HttpServlet {

    UsuarioDB usDB = new UsuarioDB();
    Usuario us = new Usuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("btnIngresar");
        if (accion.equalsIgnoreCase("Ingresar")) {
            String nombre = request.getParameter("txtUsuario");
            String contras = request.getParameter("contra");
            us = usDB.validar(nombre, contras);
            if (us.getNombre() != null && us.getActivo() == 1) {
                if (us.getRol() == 1) {
                    request.setAttribute("usuario", us);
                    request.getRequestDispatcher("Controlador?accion=fabrica").forward(request, response);
                } else if (us.getRol() == 2) {
                    request.getRequestDispatcher("Controlador?accion=Ventas").forward(request, response);
                } else if (us.getRol() == 3) {
                    request.getRequestDispatcher("Controlador?accion=Administaracion").forward(request, response);
                } else {
                    response.sendRedirect("sesion/index.jsp");
                }
            } else {
                response.sendRedirect("sesion/index.jsp");
            }
        } else {
            response.sendRedirect("sesion/index.jsp");
        }

    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
