<%-- 
    Document   : Reporte-Devolucion
    Created on : 5/09/2021, 16:48:31
    Author     : elvis_agui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="d-flex"> 
            <div class="card-body" style="background-color:#20c997 ">
                <h3 class="text-center">REPORTES DE DEVOLUCION</h3>
            </div>
        </div>
        <div class="card-body" style="background-color:activecaption ">
            <div class="card-body"> 
                <div class="card-body"> 
                    <form action="Controlador?menu=RepoDev" method="POST">
                        Fecha inicio <input class="btn btn-primary" type="date" name="fechaI">
                        Fecha final <input class="btn btn-primary" type="date" name="fechaF">
                        <input type="submit" name="accion" value="Devolucion" class="btn btn-info">
                    </form>
                </div>
                <br>
                <br>
                <h2>-----DEVOLUCIONES REGISTRADAS-----</h2>
                <table border="10" class="table table-success table-striped"> 
                    <thead>
                        <tr>
                            <th>NO. Factura</th>
                            <th>Fecha venta</th>
                            <th>ID</th>
                            <th>Producto</th>
                            <th>Precio</th>
                            <th>NIT cliente</th>
                            <th>Fecha Devolucion</th>
                            <th>Perdida</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <c:forEach var="lista" items="${lista}">
                            <tr>
                                <td>${lista.getCorrelativo()}</td>
                                <td>${lista.getFecha()}</td>
                                <td>${lista.getMueble_ensamblado()}</td>
                                <td>${lista.getNombreMueble()}</td>
                                <td>${lista.getPrecioV()}</td>
                                <td>${lista.getCliente()}</td>
                                <td>${lista.getFechaD()}</td>
                                <td>${lista.getPerdida()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> 
            </div>
            <div class="card-body"> 
                <form action="Devolucion" method="POST">
                    <input type="submit" name="accion" value="Descargar" class="btn btn-danger">
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    </body>
</html>
