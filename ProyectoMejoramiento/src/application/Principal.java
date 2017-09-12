/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;


import data.Pregunta;
import static data.Pregunta.cargarPreguntas;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import screens.IngresoPreguntasPane;

/**
 *
 * @author Juandi
 */
public class Principal extends Application{
    static ArrayList<Pregunta> questions;
    static Stage sPrimario;
    
    public static void main(String[] args) {
        //cargarPreguntas("PreguntasJava1.txt");
        launch(args);
    
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        sPrimario= primaryStage;
        Scene s = new Scene(new IngresoPreguntasPane().getRoot());
        primaryStage.setScene(s);
        primaryStage.show();

    }
    
}
