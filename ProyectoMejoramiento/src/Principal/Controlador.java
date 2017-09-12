/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

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
    
    // Declaring all UI objects
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

    // variables
    static ArrayList<Pregunta> preguntas;
    static int porcentaje;
    static int valorPausa = 1500;


    @FXML
    void initialize() {  // This gets run once to initialize the event handlers and such

        // Carga preguntas desde un txt
        preguntas = Pregunta.cargarPreguntas("PreguntasJava1.txt");

        Pregunta.setButtons(boton1, boton2, boton3, boton4);

        // Displays the first question in the GUI
        preguntas.get(Pregunta.getIndicePregunta()).mostrarPregunta(etiquetaPregunta, numeroPregunta);

        // Event handlers for the buttons calls method
        boton1.setOnAction(this::handleButtonAction);
        boton2.setOnAction(this::handleButtonAction);
        boton3.setOnAction(this::handleButtonAction);
        boton4.setOnAction(this::handleButtonAction);
    }


    // Called when a button is clicked
    private void handleButtonAction(ActionEvent event) {

        boton1.setDisable(true);
        boton2.setDisable(true);
        boton3.setDisable(true);
        boton4.setDisable(true);

        // Checks to see if user pressed the correct button; changes score
        // Also checks if all preguntas exhausted; exits if yes
        // Uses getTarget() to get the button that was clicked
        preguntas.get(Pregunta.getIndicePregunta()).verificarCorrecta((Button) event.getTarget(), preguntas, etiquetaPuntaje);

        // This is needed to pause inbetween preguntas without stopping the UI thread
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    // Displays next question
                    preguntas.get(Pregunta.getIndicePregunta()).mostrarPregunta(etiquetaPregunta, numeroPregunta);
                    boton1.setDisable(false);
                    boton2.setDisable(false);
                    boton3.setDisable(false);
                    boton4.setDisable(false);
                }); } }, valorPausa); }


    // This method is run when all preguntas have been answered and displays score
    public static void finished(int score, int questionsCorrect) {
        // Calculates porcentaje value
        if (questionsCorrect == 0) { porcentaje = 0; }
        else { porcentaje = (int) ((double)questionsCorrect/(double)preguntas.size() * 100); }

        // Creates alert; tells score and preguntas correct
        Alert finish = new Alert(Alert.AlertType.INFORMATION);
        finish.setTitle("You Win!");
        finish.setHeaderText("Score: " + score);
        finish.setContentText("Questions Correct: " + questionsCorrect + " out of " + preguntas.size() + " (" + porcentaje + "%)");
        finish.getDialogPane().getStylesheets().add(Pregunta.class.getResource("lightTheme.css").toExternalForm());
        finish.showAndWait();

        // Exits
        Platform.exit();
        System.exit(0);
    }
    
}
