/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Juandi
 */
public class Almacenamiento {
    /**
     *HashSet para preguntas
     */
    public static HashSet <Pregunta> preguntas = new HashSet();
    
    /**
     *HashMap cuyas claves sean tipo Pregunta, valores serán HashSets de sus respectivas respuestas
     */
    public static HashMap <Pregunta, HashSet<Respuesta>> mapaPR = new HashMap();    
    
    //Getters y setters correspondientes

    /**
     * Devuelve lo contenido en el HashSet preguntas.
     * @return
     */
    public static HashSet<Pregunta> getPreguntas() {
        return preguntas;
    }

    /**
     * No utilizado.
     * @param preguntas
     */
    public static void setPreguntas(HashSet<Pregunta> preguntas) {
        Almacenamiento.preguntas = preguntas;
    }

    /**
     * Obtiene claves y valores del HashMap de Pregunta/Set de Respuestas.
     * @return
     */
    public static HashMap<Pregunta, HashSet<Respuesta>> getMapaPR() {
        return mapaPR;
    }

    /**
     * No utilizado.
     * @param mapaPR
     */
    public static void setMapaPR(HashMap<Pregunta, HashSet<Respuesta>> mapaPR) {
        Almacenamiento.mapaPR = mapaPR;
    }    
    
    /**
     * Lee el archivo y agrega las preguntas con sus respuestas al programa.
     * @param fileName
     * @return
     */
    public static String cargarPreguntas(String fileName){
        String line = null;    
        try {
            //Para lectura del archivo
            FileReader fileReader =
                    new FileReader(fileName);            
            
            //BufferedReader para optimización de recursos
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            
//            Lee línea por línea, verifica que tengan algo de texto
            while(((line = bufferedReader.readLine()) != null)) {
                if ((line.length() > 0) && !(line.startsWith("//"))){
//                Para ignorar las líneas de comentario en el archivo de texto
                //if (!(line.startsWith("//"))){
//                    Crea una lista de Strings para cada línea a evaluar
                    List<String> listaLinea = new ArrayList<>(Arrays.asList(line.split(";")));
//                    Impresión de prueba del ArrayList(descomentar)
//                    System.out.println(listaLinea);
                    
                    //Obtiene lista de Respuestas, en tipo String
                    List <String >resp = listaLinea.subList(1,listaLinea.size());
                    //Creación de Respuestas (bajo el debido constructor) en un HashSet
                    HashSet<Respuesta> respuestas = new HashSet();                    
                    for (int i = 0; i < resp.size();i++){
                        if (i == 0){
                            //Identifica la respuesta correcta
                            respuestas.add(new Respuesta(resp.get(i), true));
                        }
                        else{
                            //Identifica las respuestas incorrectas
                            respuestas.add(new Respuesta(resp.get(i), false));
                        }                    
                    }
                    //System.out.println(resp); Impresión de prueba (descomentar)
                    
                    //Agrega Preguntas y Respuestas a nuestro HashSet y HashMap
                    Almacenamiento.getPreguntas().add(new Pregunta(listaLinea.get(0)));
                    Almacenamiento.getMapaPR().put(new Pregunta(listaLinea.get(0)), respuestas);    
                //}
                }
            }
            //Impresiones de prueba, descomentar.
//            for(Pregunta p: Almacenamiento.getPreguntas()){
//                System.out.println(p.toString());            
//            }            
//            Almacenamiento.getMapaPR().forEach((k,v)-> System.out.println(k+", "+v));
            
            // Siempre cerrar archivo, para optimizar recursos
            bufferedReader.close();         
        }
        //Detalle de exception impreso en consola
        catch(FileNotFoundException ex) {
            System.out.println(
                    "No se pudo abrir archivo '" +
                            fileName + "'");                
        }
        //Detalle de exception impreso en consola
        catch(IOException ex) {
            System.out.println(
                    "Error leyendo archivo '"
                            + fileName + "'");            
        }
        return null;
    }
}
