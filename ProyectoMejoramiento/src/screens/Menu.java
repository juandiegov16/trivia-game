/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import static application.Principal.sPrimario;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author Juandi
 */
public class Menu {
    private VBox root;
    Button nuevoJuego, ingresoPreguntas, baseDeDatos;

    public Menu() {
        root = new VBox();
        nuevoJuego = new Button("Nuevo Juego");
        ingresoPreguntas = new Button("Ingreso de Preguntas");
        baseDeDatos = new Button("Base de Datos");
        
        nuevoJuego.setOnMouseClicked(e -> sPrimario.setScene(new Scene(new JuegoPane().getRoot())));
        ingresoPreguntas.setOnMouseClicked(e -> sPrimario.setScene(new Scene(new IngresoPreguntasPane().getRoot())));
        baseDeDatos.setOnMouseClicked(e -> sPrimario.setScene(new Scene(new BaseDatos().getRoot())));
        
        root.getChildren().addAll(nuevoJuego, ingresoPreguntas,baseDeDatos);
        
    }

    /**
     * @return the root
     */
    public VBox getRoot() {
        return root;
    }
    
    
    
    
}
