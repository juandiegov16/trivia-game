/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Juandi
 */
public class Almacenamiento {
    //HashSet para preguntas
    public static HashSet <Pregunta> preguntas = new HashSet();
    
    //HashMap cuya clave sea preguntas, cuyo valor sea un hashSet de sus respuestas
    public static HashMap <Pregunta, HashSet<Respuesta>> mapaPR = new HashMap();    
    
    //Getters y setters correspondientes
    public static HashSet<Pregunta> getPreguntas() {
        return preguntas;
    }

    public static void setPreguntas(HashSet<Pregunta> preguntas) {
        Almacenamiento.preguntas = preguntas;
    }

    public static HashMap<Pregunta, HashSet<Respuesta>> getMapaPR() {
        return mapaPR;
    }

    public static void setMapaPR(HashMap<Pregunta, HashSet<Respuesta>> mapaPR) {
        Almacenamiento.mapaPR = mapaPR;
    }
    
    public static String cargarPreguntas(String fileName){
        String line = null;    
        try {            
            FileReader fileReader =
                    new FileReader(fileName);            
            
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            
            while(((line = bufferedReader.readLine()) != null)) {
                if (!(line.startsWith("//"))){
                    List<String> listaLinea = new ArrayList<>(Arrays.asList(line.split(";")));
                    //System.out.println(listaLinea); Impresión de prueba
                    List resp = listaLinea.subList(1,listaLinea.size());
                    Set<Respuesta> respuestas = new HashSet(resp);
                    //System.out.println(resp); Impresión de prueba

                    Almacenamiento.getPreguntas().add(new Pregunta(listaLinea.get(0)));
                    Almacenamiento.getMapaPR().put(new Pregunta(listaLinea.get(0)), (HashSet<Respuesta>) respuestas);    
                }
            }
            //Impresiones de prueba, descomentar.
//            for(Pregunta p: Almacenamiento.getPreguntas()){
//                System.out.println(p.toString());            
//            }
//            
//            Almacenamiento.getMapaPR().forEach((k,v)-> System.out.println(k+", "+v));
            
            // Siempre cerrar archivos
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "No se pudo abrir archivo '" +
                            fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                    "Error leyendo archivo '"
                            + fileName + "'");            
        }
        return null;
    }
    
    
}
