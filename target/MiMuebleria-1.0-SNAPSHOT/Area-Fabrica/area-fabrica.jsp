<%-- 
    Document   : area-fabrica
    Created on : 19/08/2021, 01:04:54
    Author     : elvis_agui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Area de Fabrica</title>
        <link rel="stylesheet" href="../css/Area-Fabrica.css"/>
        
    </head>
    <body background="../imagenes/fabrica1.jpg">
        <div id="sidebar">
            <div class="toggle-btn">
                <span>&#9776;</span> 
            </div>
            <ul>
                <li>
                    <img src="../imagenes/logo.jpg" alt="Logotipo" class="logo"/>
                </li>           
                <li>Crear Piezas ${usuario.getNombre()} </li>
                <li>Elimiar Piezas</li>
                <li>Modificar Piezas</li>
                <li>Ensamblar Muebles</li>
                <li>Registrar Muebles a la Sala de Ventas</li>
                <li>Inforamacion de Piezas</li>
                <li>Inforamacion de Muebles</li>
            </ul>
        </div> 
        <script src="../js/Area-Fabrica.js"></script>
    </body>
</html>
