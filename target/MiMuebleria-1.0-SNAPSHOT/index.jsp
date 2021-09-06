<%-- 
    Document   : index
    Created on : 16/08/2021, 13:12:38
    Author     : elvis_agui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/estilos.css">
        <title>Mi-Muebleria</title>

    </head>
    <body background="resources/imagenes/fondo.jpg">
        <div class = "login">
            <img class="logo" src="resources/imagenes/login.jpg " alt="logo-imgen"/>   
            <h1>Binevenid@ Inicia Sesion</h1>
            <form action="Validar" method="POST">
                <label for="username">Usuario</label>
                <input type="text" name="txtUsuario" placeholder="Nombre Registro" required type ="text">
                <label for="password">Contraseña</label>
                <input type="password" name="contra" placeholder="Tu contraseña" required type ="password">
                <input type="submit" name="btnIngresar" value="Ingresar">
            </form>
        </div>
    </body>
</html>
