<%-- 
    Document   : Venta
    Created on : 1/09/2021, 21:43:52
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
            <div class="col-sm-5">
                <br>
                <div class="card">
                    <div class="card-body">
                        <div class="form-group">
                            <h2>Buscar Cliente</h2>
                        </div>
                        <form action="Controlador?menu=Venta" method="POST">
                            <div class="form-group d-flex">
                                <div class="col-sm-7  d-flex">
                                    <input type="text" name="cliente" class="form-cotrol"  placeholder="NIT" required="text">
                                    <input type="submit" name="accion" value="Buscar" class="btn btn-info">
                                </div>
                                <div class="col-sm-4">
                                    <input type="text" name="accion" value="${nomEncontrado}" class="form-cotrol" placeholder="Nombre">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <form action="Controlador?menu=Venta" method="POST">
                            <div class="form-group">
                                <h2>Registrar Nuevo Cliente</h2>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-6  d-flex">
                                    <input type="text" name="NIT" class="form-cotrol"  placeholder="NIT" required="text">
                                    <input type="text" name="nombre" class="form-cotrol"  placeholder="Nombre" required="text">
                                </div>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-7">
                                    <input type="text" name="Direccion" class="form-cotrol"  placeholder="Direccion" required="text">
                                    <input type="submit" name="accion" value="registrar" class="btn btn-info">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <div class="form-group">
                            <h2>Datos del Cliente para Factura</h2>
                        </div>
                        <form action="Controlador?menu=Venta" method="POST">
                            <div class="form-group d-flex">
                                <div class="col-sm-6  d-flex">
                                    <input type="text" name="Nitcliente" class="form-cotrol"  placeholder="NIT" required="text">
                                    <input type="submit" name="accion" value="insertar" class="btn btn-info">
                                </div>
                            </div>
                        </form>
                        <div class="form-group">
                            <h2>Datos De Productos</h2>
                        </div>
                        <form action="Controlador?menu=Venta" method="POST">
                            <div class="form-group d-flex">
                                <div class="col-sm-6  d-flex">
                                    <input type="text" name="codigo" class="form-cotrol"  placeholder="identificador del Mueble">
                                    <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
            <div class="col-sm-7">
                <br>
                <div class="card">
                    <div class="card-body">
                        <div class="form-group d-flex">
                            <div class="form-group col-sm-2">
                                <h3>No. Factura</h3>
                            </div>
                            <div class="form-group col-sm-2">
                                <h3>${factura}</h3>
                            </div>
                            <div class="form-group col-sm-2">
                                <h3>NIT cliente:</h3>
                            </div>
                            <div class="form-group col-sm-2">
                                <h3>${cliente.getNit()}</h3>
                            </div>
                            <div class="form-group col-sm-2">
                                <h3>Fecha: </h3>
                            </div>
                            <div class="form-group col-sm-2">
                                <h3>${fecha}</h3>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <div class="form-group d-flex">
                            <div class="form-group col-sm-2">
                                <h3>Nombre: </h3>
                            </div>
                            <div class="form-group col-sm-3">
                                <h3>${cliente.getNombre()}</h3>
                            </div>
                            <div class="form-group col-sm-2">
                                <h3>direccion: </h3>
                            </div>
                            <div class="form-group col-sm-4">
                                <h3>${cliente.getDireccion()}</h3>
                            </div>
                        </div>
                        <table border="5" class="table table-success table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Tipo de Mueble</th>
                                    <th>precio unitrio</th>
                                </tr>
                            </thead>
                            <tbody> 
                                <c:forEach var="lista" items="${listaV}">
                                    <tr>
                                        <td>${lista.getMueble_ensamblado()}</td>
                                        <td>${lista.getNombreMueble()}</td>
                                        <td>${lista.getPrecioV()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                            <form action="Controlador?menu=Venta" method="POST">
                                <input type="password" name="usuario" placeholder="Contraseña"  required="password">
                                <input type="submit" class="btn btn-success" name="accion" value="Generar Venta" placeholder="Contraseña">
                                <input type="submit" class="btn btn-danger" name="accion" value="Abortar" placeholder="Contraseña">
                        </form>
                    </div>
                </div>
            </div>

        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>

    </body>
</html>
