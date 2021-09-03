<%-- 
    Document   : Carcha-Archivo
    Created on : 28/08/2021, 18:29:46
    Author     : elvis_agui
--%>

<%@page import="com.proyectoipc.archivos.LectorArchivio"%>
<%@page import="java.util.ArrayList"%>
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
            <div class="card-body" style="background-color:dimgrey">
                <div class="bg-danger col-sm-8 m-auto text-center p-2" style="background-color:brown ">
                    <h1 class="text-center">Carga de Archivos</h1>
                </div>
            </div>
        </div>
        <div class="d-flex"> 
            <div class="card col-sm-6" style="background-color:#6ce7b2">
                <div class="card-body" style="background-color:activecaption ">
                    <form class="col-sm-8 m-auto p-2" action="Controlador?menu=Carga" method="POST" enctype="multipart/form-data">
                        <br>
                        <br>
                        <div class="form-group">
                            <input class="btn btn-secondary" name="archivo" type="file" placeholder="Ingrese el archivo">
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <button class="btn btn-info" name="action" value="add">CARGAR</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="card col-sm-6" style="background-color: mintcream">
                <div class="card-body" style="background-color:activecaption ">
                   <table class="table-danger table-hover text-center">
                      <%
                    ArrayList<String> mensajes = new ArrayList<>();
                    mensajes = LectorArchivio.errores;
                    if (mensajes != null) {
                        for (String mensaje : mensajes) {
                            out.println(mensaje+"<br/>");
                        }
                    }else{
                        out.println("Lista De posibles Errores");
                    }
                %>
                    </tbody>
                </table>
                </div>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>


    </body>
</html>
