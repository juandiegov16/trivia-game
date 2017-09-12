/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

// Esta clase maneja las preguntas y su respectiva información
// Todas las preguntas son instancias de esta clase

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Juandi
 */
public class Pregunta {
    // variables estaticas (disponible para cualquier instancia)

    static Random rand = new Random();
    static int puntaje = 0;
    static int contadorPregunta = 1;
    static int indicePregunta = 0;
    static int preguntasCorrectas = 0;
    static ArrayList<Button> botones;
    final static String DELIMITADOR = ";";
    
    // variables especificas

    String pregunta;
    String correcta;
    ArrayList<String> equivocadas;
    Button randButton;



    // Constructor; creates "equivocadas" ArrayList which stores the wrong/decoy answers

    public Pregunta(String pregunta, String correcta, String equivocada1,
            String equivocada2, String equivocada3) {

        this.pregunta = pregunta;
        this.correcta = correcta;
        this.equivocadas = new ArrayList<>();
        this.equivocadas.add(equivocada1);
        this.equivocadas.add(equivocada2);
        this.equivocadas.add(equivocada3);
    }



    // Loads preguntas from a file and returns an ArrayList of Question objects

    public static ArrayList<Pregunta> cargarPreguntas(String filename) {
        ArrayList<Pregunta> preguntas = new ArrayList<>();

        try {
            Path filePath = Paths.get("./" + filename);
            // Lee lineas de archivo
            Files.lines(filePath).forEach(line -> {
                if (line.startsWith("//") || line.isEmpty()) { 
                // Ignora comentarios
                    return;
                }
                String[] tokens = line.split(DELIMITADOR); // Divide linea en array mediante delimitador                
                // Añade objeto Pregunta al ArrayList utilizando splits y recortando espacios excesivos
                // Adds Question object to ArrayList using split line and trims of excessive spaces
                preguntas.add(new Pregunta(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(),
                        tokens[3].trim(), tokens[4].trim()));
            });

        } catch (IOException e) {
            // Lanza alerta sobre archivo no encontrado
            Alert noEncontrado = new Alert(Alert.AlertType.ERROR);
	    noEncontrado.setTitle("Error en archivo de preguntas");
            noEncontrado.setHeaderText("Archivo de preguntas no encontrado!");
            noEncontrado.setContentText("Asegúrese de que exista un archivo txt de preguntas en el mismo directorio.");
            noEncontrado.getDialogPane().getStylesheets().add(Pregunta.class.getResource("lightTheme.css").toExternalForm());
            noEncontrado.showAndWait();
            // Termina proceso
            Platform.exit();
            System.exit(0);
        }

        // Revisa si hay preguntas en el ArrayList; de otro modo bota alerta y sale
        if (preguntas.isEmpty()) {
            Alert preguntaVacia = new Alert(Alert.AlertType.WARNING);
            preguntaVacia.setTitle("No Questions found");
            preguntaVacia.setHeaderText("No Questions were found in " + filename);
            preguntaVacia.setContentText("Make sure there are questions in " + filename + ".");
            preguntaVacia.getDialogPane().getStylesheets().add(Pregunta.class.getResource("lightTheme.css").toExternalForm());
            preguntaVacia.showAndWait();
            Platform.exit();
            System.exit(0);
        }
        return preguntas;
    }

    // Agrupa los 4 botones en un ArrayList para facilidad de acceso
    public static void setButtons(Button...buttonsArray) {
        botones = new ArrayList<>(Arrays.asList(buttonsArray));

    }

    // indicePregunta getter
    public static int getIndicePregunta() {
    return indicePregunta;
    }
    
    // Toma input del label y los botones; muestra la pregunta y sus respuesta en la GUI
    public void mostrarPregunta(Label lbl, Label etiquetaCorrecta) {        
        ArrayList<Button> botonesCopia = new ArrayList<>(botones);
        
        for (Button b : botonesCopia) {
            // Sets default style class for all
            b.getStyleClass().remove("correcta");
            b.getStyleClass().remove("equivocada");
        }

        // Setea la etiqueta con el número de pregunta correspondiente
        lbl.setText(this.pregunta);
        etiquetaCorrecta.setText("Pregunta " + contadorPregunta);

        // Genera entero aleatorio entre 0 y 3
        int randInt = rand.nextInt(4);
        randButton = botonesCopia.get(randInt);

        // Pone la respuesta correcta en su botón correspondiente
        botonesCopia.get(randInt).setText(this.correcta);
        botonesCopia.remove(randInt);
        
        // Se remueve el boton correcto para facilitar el seteo de otros botones
        //a equivocadas sin tener que validar
        
        
        
        // Revuelve el ArrayList para que los botones salgan en orden aleatorio
        Collections.shuffle(this.equivocadas);

        for (Button b : botonesCopia) {
            // Setea boton del mismo indice a equivocado
            b.setText(this.equivocadas.get(botonesCopia.indexOf(b)));
        }
    }



    // Verifica si respuesta seleccionada es correcta
    public void verificarCorrecta(Button b, ArrayList<Pregunta> preguntas, Label etiquetaPuntaje) {
        // Si es correcta, incrementa puntaje y preguntasCorrectas; cambia la etiquetaPuntaje
        if (this.randButton == b) { 
           b.getStyleClass().add("correcta");
        // Vuelve el boton verde
            puntaje += 10;
            etiquetaPuntaje.setText("Score: " + puntaje);
            preguntasCorrectas += 1;
        }
        else {
            b.getStyleClass().add("equivocada");
        // Makes button red
            this.randButton.getStyleClass().add("correcta");
        }


        // Checks if all preguntas have ben used; if yes, calls "finished" to show puntaje and exit

        if (preguntas.size() == contadorPregunta) {

            Controlador.finished(puntaje, preguntasCorrectas);

        }

        // If the program has reached this far, means that there more preguntas

        // Increments "contadorPregunta" and "indicePregunta" to keep track of the current pregunta

        // Changes current pregunta label

        contadorPregunta += 1;

        indicePregunta += 1;

    }

    
    
    
    
}
