/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoipc.archivos;

import com.proyectoipc.Entidades.Cliente;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author elvis_agui
 */
public class LectorArchivio {
    InsertsCampos opDB = new InsertsCampos();

    public void leerFichero(File archivo) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        while ((linea = br.readLine()) != null) {
            verificarEntidad(separarEnditad(linea));
        }
        fr.close();
    }

    public String[] separarEnditad(String linea) {
        String campos[] = null;
        campos = linea.split("\\(");
        return campos;
    }

    public void verificarEntidad(String campos[]) {
        if (campos[0].equals(Entidades.CLIENTE.name())) {
            insertarCliente(limpiar(campos[1]));
        }
    }

    public void insertarCliente(ArrayList<String> lista) {
        String direccionAux = "";
        Cliente inser = new Cliente();
        inser.setNombre(lista.get(0));
        inser.setNit(lista.get(1));
        if (lista.size() == 3) {
            inser.setDireccion(lista.get(2));
        }else{
             for (int i = 2; i < lista.size(); i++) {
                direccionAux += lista.get(i);
            }
             inser.setDireccion(direccionAux);
        }
        opDB.insertarCliente(inser);  
    }

    public ArrayList<String> limpiar(String linea) {
        ArrayList<String> lista = new ArrayList<>();
        String subCampo[] = null;
        String cadena = linea.substring(0, linea.length() - 1);
        subCampo = cadena.split(",");
        for (int i = 0; i < subCampo.length; i++) {
            String lineaLimp = subCampo[i].replace("\"", "");
            lista.add(lineaLimp);
        }

        return lista;
    }

}
