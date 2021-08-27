<%-- 
    Document   : Ensamblar-Mueble
    Created on : 22/08/2021, 17:13:14
    Author     : elvis_agui
--%>
<%@page import="java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

        <title>JSP Page</title>
    </head>
    <body>
        <div class="d-flex"> 
            <div class="card-body" style="background-color:#6ce7b2">
                <h3 class="text-center">Apartado de Ensamble piezas a Muebles</h3>
            </div>
        </div>
        <div class="d-flex"> 
            <div class="card col-sm-6" style="background-color:#5b7469">
                <div class="card-body">
                    <div class="card-body" style="background-color:gold">
                        <h3 class="text-center">Muebles "Sin Ensamblar"</h3>
                    </div>
                    <table class="table table-dark table-hover text-center">
                        <thead>
                            <tr>
                                <th>NOMBRE</th>
                                <th>PRECIO VENTA</th>
                                <th>OPCIONES</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="mueb" items="${muebles}">
                                <tr>
                                    <td>${mueb.getNombre()}</td>
                                    <td>${mueb.getPrecioVenta()}</td>
                                    <td>
                                        <a class="btn btn-warning" href="Controlador?menu=Ensamble&accion=AgregarM&nomM=${mueb.getNombre()}">Ensamblar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="card-body">
                    <div class="card-body" style="background-color:gold">
                        <h3 class="text-center">"Piezas"</h3>
                    </div>
                    <table class="table table-dark table-hover text-center">
                        <thead>
                            <tr>
                                <th>NOMBRE</th>
                                <th>COSTO</th>
                                <th>OPCIONES</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="piez" items="${piezas}">
                                <tr>
                                    <td>${piez.getNombre()}</td>
                                    <td>${piez.getCosto()}</td>
                                    <td>
                                        <a class="btn btn-warning" href="Controlador?menu=Ensamble&accion=AgregarP&nomPi=${piez.getNombre()}">Ensamblar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-sm-6 text-center" style="background-color:#5b7469">
                <div class="card-body">
                    <div class="card-body">
                        <div class="card-body" style="background-color:gold">
                            <h3 class="text-center">"Muebles"-"Piezas"</h3>
                        </div>

                        <table class="table table-dark table-hover text-center">
                            <thead>
                                <tr>
                                    <th>MUEBLE</th>
                                    <th>PIEZA</th>
                                </tr>
                            </thead>
                            <tbody> 
                                <tr>
                                    <td>${Mue_PI.getNombreM()}</td>
                                    <td>${Mue_PI.getNombreP()}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="card-body" style="background-color:gold">
                        <h3 class="text-center">Confirmar El Ensamble</h3>
                        <form action="Controlador?menu=Ensamble" method="POST">
                            <div class="form-group">
                                <label>Cantidad Piezas</label>
                                <br>
                                <input type="number" name="cantidadP" class="from-control">
                            </div>
                            <input type="submit" name="accion" value="Confirmar" class="btn btn-info">
                        </form>
                    </div>
                    <form action="Controlador?menu=Ensamble" method="POST">
                        <div class="form-group">
                            <br>
                            <br>
                            <label>Refrescar Tablas  --(Ya no Agregaras mas Piezas?)</label>
                            <input type="submit" name="accion" value="Refrescar" class="btn btn-warning">
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <div class="d-flex"> 
            <div class="card-body" style="background-color:#6ce7b2">
                <h3 class="text-center">Apartado de Registrar Muebles Ensamblados</h3>
            </div>
        </div>
        <div class="d-flex"> 
            <div class="card col-sm-6" style="background-color:#5b7469">
                <div class="card-body">
                    <h3 class="text-center">Registrar Ensamble</h3>
                    <table class="table table-dark table-hover text-center">
                        <thead>
                            <tr>
                                <th>NOMBRE</th>
                                <th>PRECIO VENTA</th>
                                <th>COSTO</th>
                                <th>OPCIONES</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="muebleE" items="${mueblesEnsam}">
                                <tr>
                                    <td>${muebleE.getNombre()}</td>
                                    <td>${muebleE.getPrecioVenta()}</td>
                                    <td>${muebleE.getCosto()}</td>
                                    <td>
                                        <a class="btn btn-warning" href="Controlador?menu=Ensamble&accion=mubleEnsamblado&nomEns=${muebleE.getNombre()}">Ensamblar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card col-sm-6" style="background-color:#5b7469">
                <div class="card-body">
                    <div class="card-body" style="background-color:gold">
                        <h3 class="text-center">Confirmacion</h3>
                    </div>
                    <table class="table table-dark table-hover text-center">
                        <thead>
                            <tr>
                                <th>MUEBLE</th>
                                <th>FECHA</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <tr>
                                <td>${muebleEnsable.getMueble()}</td>
                                <td>${fecha}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="card-body">
                    <div class="card-body" style="background-color:gold">
                        <h3 class="text-center">CONFIRMACION PARA MAYOR SEGUIRDAD</h3>
                        <form action="Controlador?menu=Ensamble" method="POST" class="text-center">
                            <div class="form-group">
                                <label>ingrese su contraseña</label>
                                <br>
                                <input type="password" name="ensamblador" class="from-control">
                            </div>
                            <br>
                            <input type="submit" name="accion" value="Registrar" class="btn btn-info">
                        </form>
                    </div>
                </div>
                <div class="card-body">
                    <div class="card-body"> 
                        <h3 class="text-center">PIEZAS AGOTADAS</h3>
                        <table class="table table-dark table-hover text-center">
                            <thead>
                                <tr>
                                    <th>NOMBRE</th>
                                    <th>CANTIDAD</th>
                                    <th>COSTO</th>
                                </tr>
                            </thead>
                            <tbody> 
                                <c:forEach var="piez" items="${piezasAgot}">
                                    <tr>
                                        <td>${piez.getNombre()}</td>
                                        <td>${piez.getCantidad()}</td>
                                        <td>${piez.getCosto()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
</body>
</html>
