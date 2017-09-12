/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.HashMap;
import java.util.HashSet;

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
    
    
    
}
