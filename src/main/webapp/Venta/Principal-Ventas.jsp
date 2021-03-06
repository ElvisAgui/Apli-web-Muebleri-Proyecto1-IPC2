<%-- 
    Document   : Principal-Ventas
    Created on : 1/09/2021, 20:34:54
    Author     : elvis_agui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <title>Area de Ventas</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-info">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">VENTAS</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-light" href="Controlador?menu=Venta&accion=nada" target="myFrame">Venta</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-light" href="Controlador?menu=devolucion&accion=nada" target="myFrame">Devolucion</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-light" href="Controlador?menu=Registro&accion=lisatSala" target="myFrame">Oferta</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-light" href="Controlador?menu=infoPiezas&accion=listaP" target="myFrame">Reportes</a>
                    </li>
                </ul>
            </div>
            <div class="dropdown">
                <form action="Validar" method="post">
                    <button name="btnIngresar" value="Salir" type="submit" class="btn  btn-warning">Cerrar sesion ${usuario.getNombre()}</button>
                </form>
            </div>
        </nav>
        <div class="m-1" style="height: 540px;">
            <iframe name="myFrame" style="border:8px; height: 100%; width: 100%;"></iframe>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>

    </body>
</html>
