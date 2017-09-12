/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;


import static data.Almacenamiento.cargarPreguntas;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import screens.IngresoPreguntasPane;

/**
 *
 * @author Juandi
 */
public class Principal extends Application{
    public static Stage sPrimario;
    
    public static void main(String[] args) {        
        cargarPreguntas("PreguntasJava1.txt");
        launch(args);
        Platform.exit();
    
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        sPrimario = primaryStage;
        Scene s = new Scene(new IngresoPreguntasPane().getRoot());
        primaryStage.setScene(s);
        primaryStage.show();

    }
    
}
