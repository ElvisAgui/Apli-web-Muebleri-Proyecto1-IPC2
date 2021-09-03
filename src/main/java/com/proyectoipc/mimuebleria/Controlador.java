/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoipc.mimuebleria;

import com.proyectoipc.Entidades.*;
import com.proyectoipc.archivos.InsertsCampos;
import com.proyectoipc.modelo.*;
import com.proyectoipc.archivos.LectorArchivio;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author elvis_agui
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class Controlador extends HttpServlet {

    LocalDateTime localDate = LocalDateTime.now();
    DateTimeFormatter ad = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    Cliente cliF = new Cliente();
    VentaSQL retrs = new VentaSQL();
    Venta vtn;
    List<Venta> listaV = new ArrayList<>();
    String fac = VentaSQL.nomFac();
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
        switch (menu) {
            case "fabrica":
                request.getRequestDispatcher("Area-Fabrica/Principal-Frabricacion.jsp").forward(request, response);
                break;
            case "Admin":
                request.getRequestDispatcher("Admin/Princiapal-Administracion.jsp").forward(request, response);
                break;
            case "Ventas":
                request.getRequestDispatcher("Venta/Principal-Ventas.jsp").forward(request, response);
                break;
            case "Crear":
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
                break;
            case "Ensamble":
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
                         {
                            try {
                                mEnsambel.setFecha(Ensamble.getFecha(ad.format(localDate)));
                            } catch (ParseException ex) {
                                System.out.println("error en fecha");
                            }
                        }
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
                break;
            case "Registro":
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
                break;
            case "infoPiezas":
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
                break;

            case "infoMueble":
                List listaMu;
                switch (accion) {
                    case "listaM":
                        listaMu = consul.infoMCreados(false, true);
                        request.setAttribute("infoMue", listaMu);
                        break;
                    case "ordenAsen":
                        listaMu = consul.infoMCreados(true, true);
                        request.setAttribute("infoMue", listaMu);
                        break;
                    case "ordenDes":
                        listaMu = consul.infoMCreados(true, false);
                        request.setAttribute("infoMue", listaMu);
                        break;
                }
                request.getRequestDispatcher("Area-Fabrica/Info-Mueble.jsp").forward(request, response);
                break;

            case "Carga":
                request.getRequestDispatcher("Admin/Carcha-Archivo.jsp").forward(request, response);
                String acciont = request.getParameter("action");
                if (acciont.equals("add")) {
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
                break;
            case "Reportes":
                ReportesSQL repo = new ReportesSQL();
                switch (accion) {
                    case "nada":
                        String hola = "hola";
                        request.setAttribute("hola", hola);
                        break;
                    case "Filtrar":
                        String fechaI = request.getParameter("fechaI");
                        String fechaF = request.getParameter("fechaF");
                        repo.setFechaI(fechaI);
                        repo.setFechaF(fechaF);
                        break;
                    case "RVenta":
                        ArrayList<String> lisR = repo.obtenerCorrelativoVenta();
                        request.setAttribute("correlativos", lisR);
                        request.setAttribute("VentasR", repo.listaVenta(lisR));
                        break;
                }
                request.getRequestDispatcher("Admin/Reportes.jsp").forward(request, response);

                break;
            case "Venta":
                request.setAttribute("fecha", ad.format(localDate));
                request.setAttribute("factura", fac);
                switch (accion) {
                    case "nada":
                        request.setAttribute("nada", new String("hola"));
                        break;
                    case "Buscar":
                        String ClienteEncontrado = retrs.existeCliente(request.getParameter("cliente"));
                        request.setAttribute("nomEncontrado", ClienteEncontrado);
                        break;
                    case "registrar":
                        InsertsCampos in = new InsertsCampos();
                        Cliente cli = new Cliente(request.getParameter("NIT"), request.getParameter("nombre"), request.getParameter("Direccion"));
                        in.insertarCliente(cli);
                        break;
                    case "insertar":
                        if (!retrs.existeCliente(request.getParameter("Nitcliente")).equals("NO ESTA REGISTRADO")) {
                            cliF = retrs.clienteF(request.getParameter("Nitcliente"));
                        }
                        request.setAttribute("cliente", cliF);
                        break;
                    case "Agregar":
                        if (!retrs.existeEnsamble(request.getParameter("codigo")).equals("no")) {
                            vtn = new Venta();
                            vtn.setCliente(cliF.getNit());
                            vtn.setMueble_ensamblado(request.getParameter("codigo"));
                            vtn.setGanancia(retrs.ganaciaV(request.getParameter("codigo")));
                            vtn.setCorrelativo(fac);
                            vtn.setNombreMueble(retrs.existeEnsamble(request.getParameter("codigo")));
                            vtn.setPrecioV(retrs.precioV(retrs.existeEnsamble(request.getParameter("codigo"))));
                            try {
                                vtn.setFecha(Ensamble.getFecha(ad.format(localDate)));
                            } catch (ParseException ex) {
                                System.out.println("error fecha");
                            }
                        }
                        if (!(retrs.repetidoLista(vtn.getMueble_ensamblado(), (ArrayList<Venta>) listaV))) {
                            listaV.add(vtn);
                        }
                        request.setAttribute("listaV", listaV);
                        request.getRequestDispatcher("Controlador?menu=Venta&accion=insertar").forward(request, response);
                        break;
                    case "Abortar":
                        listaV.clear();
                        break;
                    case "Generar Venta":
                        if (!(listaV == null || listaV.size() == 0)) {
                            if (dbAux.existeUsuario(request.getParameter("usuario"))) {
                                for (Venta venta : listaV) {
                                    retrs.insertarVenta(venta, request.getParameter("usuario"));
                                }
                                listaV.clear();
                                fac = VentaSQL.nomFac();
                            }
                        }
                        break;
                }
                request.getRequestDispatcher("Venta/Venta.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("sesion/index.jsp");
                break;
        }

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
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
