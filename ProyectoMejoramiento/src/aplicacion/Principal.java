/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import static datos.Almacenamiento.cargarPreguntas;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import pantallas.Menu;

/**
 * @author Juandi
 */
public class Principal extends Application{
    /**
     *El stage donde mostraremos todas las posibles pantallas del juego.
     */
    public static Stage sPrimario;
    
    /**
     * El espinazo de la ejecución del juego.
     * @param args
     */
    public static void main(String[] args) {
        //Aquí lee el archivo y agrega su contenido al programa
        cargarPreguntas("PreguntasJava1.txt");
        launch(args);
        //Descomentar para hacer pruebas forzando cierre después de ciertos procesos(NO REMOVER IMPORT!)
        //Platform.exit();    
    }
    
    /**
     * Override requerido al extender Application;
     * Ejecuta lo correspondiente a JavaFX.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        sPrimario = primaryStage;
        //Nos muestra la pantalla Menu, para comenzar. 
        Scene s = new Scene(new Menu().getRoot());
        sPrimario.setTitle("Quién quiere pasar POO?");
        sPrimario.setOnCloseRequest((event -> {  // Mata el TimerTask para mostrar la siguiente pregunta
            Platform.exit();
            System.exit(0);
        }));
        sPrimario.setScene(s);
        sPrimario.show();
    }    
    
    //TODO: Dibujar UML
}
