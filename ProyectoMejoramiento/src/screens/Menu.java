/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author Juandi
 */
public class Menu {
    HBox root;
    Button nuevoJuego, ingresoPreguntas, baseDeDatos;

    public Menu() {
        root = new HBox();
        nuevoJuego = new Button("Nuevo Juego");
        ingresoPreguntas = new Button("Ingreso de Preguntas");
        baseDeDatos = new Button("Base de Datos");
        
    }
    
    
    
}
