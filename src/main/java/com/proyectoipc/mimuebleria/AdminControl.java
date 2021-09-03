package com.proyectoipc.mimuebleria;

import com.proyectoipc.archivos.LectorArchivio;
import java.io.File;
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
public class AdminControl extends HttpServlet {

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
        String accion = request.getParameter("action");
        if (accion.equals("add")) {
            LectorArchivio lec = new LectorArchivio();
            Part file = request.getPart("archivo");
            String nombreArchivo = file.getSubmittedFileName();
            String path = this.getServletConfig().getServletContext().getRealPath("/archivo");
            File directorio = new File(path);
            System.out.println(path);
            if (!directorio.exists()) {
                directorio.mkdir();
            }
            file.write(path + "/" + nombreArchivo);
            File archivo = new File(path + "/" + nombreArchivo);
            lec.leerFichero(archivo);
            request.getRequestDispatcher("Admin/Carcha-Archivo.jsp").forward(request, response);
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
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        switch (menu) {
            case "Admin":
                request.getRequestDispatcher("Admin/Princiapal-Administracion.jsp").forward(request, response);

                break;
            case "Carga":
                request.getRequestDispatcher("Admin/Carcha-Archivo.jsp").forward(request, response);
                break;
        }
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
