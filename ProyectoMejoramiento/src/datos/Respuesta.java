/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.Objects;

/**
 *
 * @author Juandi
 */
public class Respuesta{

    /**
     *Texto de la respuesta.
     */
    public String texto;

    /**
     * Valor de verdad de la respuesta; Crucial para el funcionamento del juego,
     * dado que es un juego de trivia.
     */
    public boolean esCorrecta;
    
    /**
     * Constructor con dos atributos
     * @param texto
     * @param esCorrecta
     */
    public Respuesta(String texto, boolean esCorrecta){
        this.texto = texto;
        this.esCorrecta = esCorrecta;        
    }
    
    //Reescrito toString(), para poder crear los botones
    @Override
    public String toString() {
        return texto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.texto);
        return hash;
    }

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
        final Respuesta other = (Respuesta) obj;
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        return true;
    }
}
