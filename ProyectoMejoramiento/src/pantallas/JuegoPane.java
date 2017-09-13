/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import datos.Pregunta;
import java.util.ArrayList;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Juandi
 */
public class JuegoPane {
       BorderPane root;
       //TODO: Añadir elementos de juego
       
    /**
     * Pantalla de juego.
     */
    public JuegoPane(){
        root = new BorderPane();
        //TODO: Inicializar elementos de juego
        
        
    
    
    }   
    
    //TODO: Crear metodo para verificar preguntas y respuestas.
    //TODO: Crear metodo para determinar el puntaje y lidiar con los puntos seguros.
    //TODO: Crear metodo para comodin 50/50
    //TODO: Crear metodo para comodin de Pregunta al Publico

    /**
     * Getter requerido para transicion a escena JuegoPane
     * @return
     */

    public BorderPane getRoot() {
        return root;
    }
       
       
    
}
