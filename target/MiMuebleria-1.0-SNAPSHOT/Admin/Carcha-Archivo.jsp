<%-- 
    Document   : Carcha-Archivo
    Created on : 28/08/2021, 18:29:46
    Author     : elvis_agui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

        <title>JSP Page</title>
    </head>
    <body>
        <h1>Carga de datos</h1>
        <div class="container p-5">
            <div class="row flex-column justify-content-center">
                <h1 class="bg-danger col-sm-8 m-auto text-center p-2">Subir Archivo</h1>
                <form class="col-sm-8 m-auto p-2" action="../Archivo" method="POST" enctype="multipart/form-data">
                    <div class="form-group">
                        <input class="form-contro" name="archivo" type="file" placeholder="Ingrese el archivo">
                    </div>
                    <div class="form-group">
                        <button class="btn btn-succes" name="action" value="add">SUBIR</button>
                    </div>
                </form>
            </div>
        </div> 

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>


    </body>
</html>
