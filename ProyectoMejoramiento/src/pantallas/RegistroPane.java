/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import javafx.scene.layout.VBox;

/**
 *
 * @author Juandi
 */
public class RegistroPane{
    VBox root;
    //TODO: Crear resto de elementos
    
    /**
     * Permite ingresar y serializar usuarios/puntajes.
     */
    public RegistroPane(){
        root = new VBox();
        //TODO: Inicializar elementos del Pane
    
    
    }
    
    //TODO: Crear metodo para serializar usuarios y puntajes.   

    /**
     * Getter requerido para transicion a escena RegistroPane.
     * @return
     */
    public VBox getRoot() {
        return root;
    }
    
    
    

}
