/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import application.Pregunta;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Juandi
 */
public class Controlador {
    // variables
    static ArrayList<Pregunta> preguntas;
    static int porcentaje;
    static int valorPausa = 1500;
    // Muestra puntaje cuando se responden todas las preguntas
    public static void terminado(int puntaje, int preguntasCorrectas) {
        // Calcula porcentaje de correctas
        if (preguntasCorrectas == 0) { porcentaje = 0; }
        else { porcentaje = (int) ((double)preguntasCorrectas/(double)preguntas.size() * 100); }
        
        // Crea alerta; preguntas correctas
        Alert finish = new Alert(Alert.AlertType.INFORMATION);
        finish.setTitle("Has ganado!");
        finish.setHeaderText("Puntaje: " + puntaje);
        finish.setContentText("Preguntas correctas: " + preguntasCorrectas + " de " + preguntas.size() + " (" + porcentaje + "%)");
        finish.getDialogPane().getStylesheets().add(Pregunta.class.getResource("resources/lightTheme.css").toExternalForm());
        finish.showAndWait();        
        // Exits
        Platform.exit();
        System.exit(0);
    }
    
    // Declara los objetos de la UI
    @FXML
    private Label etiquetaPregunta;

    @FXML
    private Button boton1;

    @FXML
    private Button boton2;

    @FXML
    private Button boton3;

    @FXML
    private Button boton4;

    @FXML
    private Label numeroPregunta;

    @FXML
    private Label etiquetaPuntaje;



    @FXML
    void initialize() {

        // Carga preguntas desde un txt
        preguntas = Pregunta.cargarPreguntas("PreguntasJava1.txt");

        Pregunta.setButtons(boton1, boton2, boton3, boton4);

        // Muestra primera pregunta en el GUI
        preguntas.get(Pregunta.getIndicePregunta()).mostrarPregunta(etiquetaPregunta, numeroPregunta);

        // Event handlers for the buttons calls method
        boton1.setOnAction(this::handleButtonAction);
        boton2.setOnAction(this::handleButtonAction);
        boton3.setOnAction(this::handleButtonAction);
        boton4.setOnAction(this::handleButtonAction);
    }


    // Llamado cuando se hace clic a un boton
    private void handleButtonAction(ActionEvent event) {

        boton1.setDisable(true);
        boton2.setDisable(true);
        boton3.setDisable(true);
        boton4.setDisable(true);

        // Verifica si se presiono el boton correcto; cambia puntaje
        // Tambien revisa si se han gastado todas las preguntas, en cuyo caso sale
        // Utiliza getTarget() para obtener el boton presionado
        preguntas.get(Pregunta.getIndicePregunta()).verificarCorrecta((Button) event.getTarget(), preguntas, etiquetaPuntaje);

        // Necesario para pausar entre preguntas sin detener la UI
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    // Muestra siguiente pregunta
                    preguntas.get(Pregunta.getIndicePregunta()).mostrarPregunta(etiquetaPregunta, numeroPregunta);
                    boton1.setDisable(false);
                    boton2.setDisable(false);
                    boton3.setDisable(false);
                    boton4.setDisable(false);
                });
            }
        }, valorPausa);
    }


    
}
