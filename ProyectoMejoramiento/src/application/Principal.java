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
import screens.Menu;

/**
 *
 * @author Juandi
 */
public class Principal extends Application{
    public static Stage sPrimario;
    
    public static void main(String[] args) {
        //Aquí lee el archivo y agrega su contenido al programa
        cargarPreguntas("PreguntasJava1.txt");
        //Ejecuta lo pertinente a JavaFX
        launch(args);
        //Descomentar para hacer pruebas forzando cierre después de ciertos
        //procesos
        //Platform.exit();
    
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        sPrimario = primaryStage;
        //Nos muestra la pantalla Menu, para comenzar.
        Scene s = new Scene(new Menu().getRoot());
        primaryStage.setTitle("Quién quiere pasar POO?");
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
