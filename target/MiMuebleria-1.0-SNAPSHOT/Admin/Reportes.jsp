<%-- 
    Document   : Reportes
    Created on : 31/08/2021, 01:09:22
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
                <h3 class="text-center">AREA DE REPORTES</h3>
            </div>
        </div>
        <div class="card-body" style="background-color:activecaption ">
            <div class="card-body"> 
                <div class="card-body"> 
                    <form action="Controlador?menu=Reportes" method="POST">
                        Fecha inicio <input class="btn btn-primary" type="date" name="fechaI">
                        Fecha final <input class="btn btn-primary" type="date" name="fechaF">
                        <input type="submit" name="accion" value="Filtrar" class="btn btn-info">
                    </form>
                </div>
                <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                    <a class="btn btn-danger" href="Controlador?menu=Reportes&accion=RVenta">Ventas</a>
                    <a class="btn btn-success" href="Controlador?menu=infoMueble&accion=ordenAsen">Devolucion</a>
                    <a class="btn btn-danger" href="Controlador?menu=infoMueble&accion=ordenAsen">Ganancia</a>
                    <a class="btn btn-success" href="Controlador?menu=infoMueble&accion=ordenAsen">Usuario Venta</a>
                    <a class="btn btn-danger" href="Controlador?menu=infoMueble&accion=ordenDes">Usuario Ganancia</a>
                    <a class="btn btn-success" href="Controlador?menu=infoMueble&accion=ordenDes">Mueble Mas Vendido</a>
                    <a class="btn btn-danger" href="Controlador?menu=infoMueble&accion=ordenDes">Mueble Menos Vendido</a>
                </div>
                <br>
                <br>
                <c:forEach var="Corr" items="${correlativos}">
                    <h2>${Corr}</h2>
                    <table border="10" class="table table-success table-striped"> 
                        <thead>
                            <tr>
                                <th>Fecha</th>
                                <th>Producto</th>
                                <th>Precio</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="lista" items="${VentasR}">
                                <c:if test="${lista.getCorrelativo() == Corr}">
                                    <tr>
                                        <td>${lista.getFecha()}</td>
                                        <td>${lista.getMueble_ensamblado()}</td>
                                        <td>${lista.getGanancia()}</td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:forEach>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
    </body>
</html>
