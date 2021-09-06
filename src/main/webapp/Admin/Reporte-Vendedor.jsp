<%-- 
    Document   : Reporte-Vendedor
    Created on : 5/09/2021, 22:46:40
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
                <h3 class="text-center">REPORTE DE VENTAS DE USUARIOS</h3>
            </div>
        </div>
        <div class="card-body" style="background-color:activecaption ">
            <div class="card-body"> 
                <div class="card-body"> 
                    <form action="Controlador?menu=RVendedor" method="POST">
                        Fecha inicio <input class="btn btn-primary" type="date" name="fechaI">
                        Fecha final <input class="btn btn-primary" type="date" name="fechaF">
                        <input type="submit" name="accion" value="Vendedor" class="btn btn-info">
                    </form>
                </div>
                <br>
                <br>
                <h2>USUARIO CON MAS VENTAS--------> ${usuario}</h2>
                <table border="10" class="table table-success table-striped"> 
                    <thead>
                        <tr>
                            <th>Fecha venta</th>
                            <th>ID Mueble</th>
                            <th>Producto</th>
                            <th>Precio</th>
                        </tr>
                    </thead>
                    <tbody> 
                    <c:forEach var="lista" items="${lista}">
                        <tr>
                            <td>${lista.getFecha()}</td>
                            <td>${lista.getMueble_ensamblado()}</td>
                            <td>${lista.getNombreMueble()}</td>
                            <td>${lista.getPrecioV()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table> 
            </div>
            <div class="card-body"> 
                <form action="VendedorV" method="POST">
                    <input type="hidden" name="usuario" value="${usuario}">
                    <input type="submit" name="accion" value="Descargar" class="btn btn-danger">
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    </body>
</html>
