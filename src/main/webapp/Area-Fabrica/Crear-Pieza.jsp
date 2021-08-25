<%-- 
    Document   : Crear-Pieza
    Created on : 22/08/2021, 17:12:34
    Author     : elvis_agui
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <title>Crear-Pieza</title>
    </head>
    <body>
        <div class="d-flex"> 
            <div class="card col-sm-5" style="background-color:#6ce7b2">
                <div class="card-body">
                    <h3>Campos para Creacion "Pieza Nueva"</h3>
                    <form action="Controlador?menu=Crear" method="POST">
                        <div class="form-group">
                            <label>Nombre</label>
                            <br>
                            <input type="text" name="nombreN" class="from-control">
                        </div>
                        <div class="form-group">
                            <label>Costo</label>
                            <br>
                            <input type="text" name="costoN" class="from-control">
                        </div>
                        <div class="form-group">
                            <label>Cantidad</label>
                            <br>
                            <input type="text" name="cantidadN" class="from-control">
                        </div>
                        <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                    </form>
                    <br>
                    <br>
                    <h3>Campos para Actualiza Pieza </h3>
                    <form action="Controlador?menu=Crear" method="POST">
                        <div class="form-group">
                            <label>Nombre</label>
                            <br>
                            <input type="text" value="${piezaA.getNombre()}" name="nombreE" class="from-control">
                        </div>
                        <div class="form-group">
                            <label>Costo</label>
                            <br>
                            <input type="text" value="${piezaA.getCosto()}" name="costoE" class="from-control">
                        </div>
                        <div class="form-group">
                            <label>Cantidad</label>
                            <br>
                            <input type="text"  value="${piezaA.getCantidad()}" name="cantidadE" class="from-control">
                        </div>
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-info">
                    </form>
                    <br>
                </div>
            </div>
            <div class="col-sm-7" style="background-color:#5b7469">
                <h3 class="text-center">Piezas Agotadas</h3>
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
                <h3 class="text-center">Inventario de Piezas</h3>
                <table class="table table-dark table-hover text-center">
                    <thead>
                        <tr>
                            <th>NOMBRE</th>
                            <th>CANTIDAD</th>
                            <th>COSTO</th>
                            <th>OPCION</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <c:forEach var="piez" items="${piezas}">
                            <tr>
                                <td>${piez.getNombre()}</td>
                                <td>${piez.getCantidad()}</td>
                                <td>Q ${piez.getCosto()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Crear&accion=Editar&nom=${piez.getNombre()}">Modificar</a>
                                    <a class="btn  btn-danger" href="Controlador?menu=Crear&accion=Eliminar&nom=${piez.getNombre()}">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
    </body>
</html>
