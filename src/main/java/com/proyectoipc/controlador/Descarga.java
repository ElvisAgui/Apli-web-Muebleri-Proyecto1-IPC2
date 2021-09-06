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
public class Descarga extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        String pHat = escritor.escribirArchivo((ArrayList<Venta>) Controlador.listaV1, path, false);
        try {
            BufferedInputStream file = new BufferedInputStream(new FileInputStream(pHat));
            response.setContentType("text/csv;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=ReporteVenta.csv");
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
