<%-- 
    Document   : Princiapal-Administracion
    Created on : 28/08/2021, 13:53:47
    Author     : elvis_agui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

        <title>Area Administrativa</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">ADMINISTRACION</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-light" href="Controlador?menu=Carga" target="myFrame">SubirArchivo</a>
                    </li>
                    <li class="nav-item">
                        <div class="dropdown">
                            <button class="btn btn-outline-light" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                Area Reportes
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <li><a class="dropdown-item" href="Controlador?menu=Reportes&accion=nada" target="myFrame">Reporte Venta</a></li>
                                <li><a class="dropdown-item" href="Controlador?menu=RepoDev&accion=nada" target="myFrame">Reporte Devolucion</a></li>
                                <li><a class="dropdown-item" href="Controlador?menu=Rganancia&accion=nada" target="myFrame">Reporte Ganancias</a></li>                                
                                <li><a class="dropdown-item" href="Controlador?menu=RVendedor&accion=nada" target="myFrame">Reporte Usuarios</a></li>                                
                                <li><a class="dropdown-item" href="Controlador?menu=RMueble&accion=nada" target="myFrame">Mueble Mas Vendido</a></li>                                
                                <li><a class="dropdown-item" href="Controlador?menu=RMuebleP&accion=nada" target="myFrame">Mueble menos Vendido </a></li>                                
                            </ul>
                        </div>

                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-light" href="Controlador?menu=Registro&accion=lisatSala" target="myFrame">En Venta</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-light" href="Controlador?menu=infoPiezas&accion=listaP" target="myFrame">Info Piezas</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-light" href="Controlador?menu=infoMueble&accion=listaM" target="myFrame">Info Muebles</a>
                    </li>
                </ul>
            </div>
            <div class="dropdown">
                <form action="Validar" method="post">
                    <button name="btnIngresar" value="Salir" type="submit" class="btn  btn-danger">Cerrar sesion ${usuario.getNombre()}</button>
                </form>
            </div>
        </nav>
        <div class="m-1" style="height: 540px;">
            <iframe name="myFrame" style="border:8px dotted black; height: 100%; width: 100%;"></iframe>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    </body>
</html>
