/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

// Esta clase maneja las preguntas y su respectiva información

import java.util.Objects;


/**
 *
 * @author Juandi
 */
public class Pregunta {    
    String enunciado;
    
    //Constructor con un atributo, facilita creación
    public Pregunta (String enunciado){
        this.enunciado = enunciado;
    }

    //Reescrito el toString()
    @Override
    public String toString() {
        return enunciado;
    }
    
    //Reescrito hashCode()
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.enunciado);
        return hash;
    }
    
    //Reescrito el equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pregunta other = (Pregunta) obj;
        if (!Objects.equals(this.enunciado, other.enunciado)) {
            return false;
        }
        return true;
    }
}
