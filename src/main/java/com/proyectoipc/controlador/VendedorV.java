/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoipc.controlador;

import com.proyectoipc.Entidades.Venta;
import com.proyectoipc.archivos.EscritorArchivos;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elvis_agui
 */
public class VendedorV extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String path = this.getServletConfig().getServletContext().getRealPath("/archivo");
        File directorio = new File(path);
        System.out.println(path);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        EscritorArchivos escritor = new EscritorArchivos();
        String pHat = escritor.EscArchivoUsuario((ArrayList<Venta>) Controlador.listaV1, path, request.getParameter("mueble"));
        try {
            BufferedInputStream file = new BufferedInputStream(new FileInputStream(pHat));
            response.setContentType("text/csv;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=ReporteUsuario.csv");
            int data = file.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = file.read();
            }
            file.close();
        } catch (Exception e) {
            System.out.println("erro en descargar");
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
