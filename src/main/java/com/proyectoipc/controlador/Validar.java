/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoipc.controlador;

import com.proyectoipc.modelo.Usuario;
import com.proyectoipc.modelo.ConsulDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author elvis_agui
 */
public class Validar extends HttpServlet {

    ConsulDB usDB = new ConsulDB();
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
            if (us.getNombre() != null && us.getActivo()) {
                switch (us.getRol()) {
                    case 1:
                        request.setAttribute("usuario", us);
                        request.getRequestDispatcher("Controlador?menu=fabrica").forward(request, response);
                        break;
                    case 2:
                        request.setAttribute("usuario", us);
                        request.getRequestDispatcher("Controlador?menu=Ventas").forward(request, response);
                        break;
                    case 3:
                        request.setAttribute("usuario", us);
                        request.getRequestDispatcher("AdminControl?menu=Admin").forward(request, response);
                        break;
                    default:
                        response.sendRedirect("sesion/index.jsp");
                        break;
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
