<%-- 
    Document   : Devolucion
    Created on : 2/09/2021, 22:28:56
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
        <!-- interfas para la devolucion -->
        <div class="d-flex"> 
            <div class="col-sm-4">

            </div>
            <div class="col-sm-5">
                <br>
                <div class="card">
                    <div class="card-body">
                        <!-- form para envio de datos -->
                        <div class="form-group">
                            <h2>DATOS PARA LA DEVOLUCION</h2>
                        </div>
                        <form action="Controlador?menu=devolucion" method="POST">
                            <div class="form-group d-flex">
                                <div class="col-sm-7  d-flex">
                                    <input type="text" name="idMueble" class="form-cotrol"  placeholder="codigo Mueble" required="text">
                                    <input type="text" name="corrlativo" class="form-cotrol"  placeholder="Factura" required="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <h2>Confirmacion Con Contraseña</h2>
                            </div>
                            <div class="form-group">
                                <input type="password" name="usuario" class="form-cotrol" placeholder="contraseña" required="password">
                                <input type="submit" name="accion" value="devolver" class="btn btn-info">
                            </div>
                            <div class="form-group">
                                <h2>Status </h2>
                            </div>
                            <div class="form-group">
                                <h3> ${status} </h3>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-3">

        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>

</body>
</html>
