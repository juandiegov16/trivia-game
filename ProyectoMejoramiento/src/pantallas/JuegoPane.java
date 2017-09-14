/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import datos.Almacenamiento;
import datos.Pregunta;
import datos.Respuesta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Juandi
 */
public class JuegoPane {
   //Variables estaticas (disponibles para cualquier instancia)
   static int puntaje = 0;
   static int contadorPregunta = 1;
   static int preguntasCorrectas = 0;
   static boolean statusAprobado;
   
   
   //Elementos de JavaFX
   BorderPane root;
   public Label lblPregunta;
   public Label numeroPregunta;
   Label lblPuntaje;
   Button btn50, btnPAP; 
   HBox comodines;
   VBox box;       

   ArrayList<Pregunta> pregsJuego;
   public HashMap <Button, Respuesta> mapaBotonesRespuesta;
   int cantidadPreguntas;
   static int valorPausa = 1500;       
              
    /**
     * Pantalla de juego.
     */
    public JuegoPane(){
        pregsJuego = new ArrayList<>(Almacenamiento.getPreguntas());
        Collections.shuffle(pregsJuego);
        cantidadPreguntas = pregsJuego.size();
        mapaBotonesRespuesta = new HashMap();
        root = new BorderPane();
        root.setPrefSize(600,260);
        lblPregunta = new Label();
        numeroPregunta = new Label();
        lblPuntaje = new Label();
        btn50 = new Button("50/50");
        btnPAP = new Button("Preguntar al Público");
        comodines = new HBox();
        
        root.setTop(lblPregunta);
        lblPregunta.setAlignment(Pos.CENTER);
        box = new VBox();        
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);                
        root.setCenter(box);
        btn50.setOnAction(e-> comodin50());
        btnPAP.setOnAction(e ->comodinPAP());
        
        comodines.getChildren().addAll(btn50, btnPAP, numeroPregunta, lblPuntaje);
        comodines.setSpacing(10);
        root.setBottom(comodines);
        
        mostrarPregunta(lblPregunta, numeroPregunta);       
    }   
    
    public void mostrarPregunta(Label lblPregunta, Label lblCorrecta){
        box.getChildren().clear();
        mapaBotonesRespuesta.clear();
//        System.out.println(pregsJuego); //Impresión de prueba del ArrayList
        int numero = new Random().nextInt(cantidadPreguntas);
                Pregunta pregunta = pregsJuego.get(new Random().nextInt(cantidadPreguntas));
                System.out.println(pregunta); //Impresión de prueba
                pregsJuego.remove(pregunta);
                cantidadPreguntas --;
                System.out.println(cantidadPreguntas);
                
                if (preguntasCorrectas == 10){
                    puntaje = 100; alertaPuntaje();
                }
                
                for(Respuesta respuesta: Almacenamiento.mapaPR.get(pregunta)){
                    Button btnRespuesta = new Button(respuesta.toString());
                    box.getChildren().add(btnRespuesta);
                    btnRespuesta.setOnAction((event) -> this.comportamientoBoton(event));
                    if (!mapaBotonesRespuesta.containsKey(btnRespuesta) && !mapaBotonesRespuesta.containsValue(respuesta)){
                        mapaBotonesRespuesta.put(btnRespuesta, respuesta);}}
                
                lblPregunta.setText(pregunta.toString());
                lblCorrecta.setText("Pregunta " + (contadorPregunta));            
    }
    
    private void comportamientoBoton(ActionEvent event){
        ((Node)event.getSource()).setDisable(true);
        verificarCorrecta((Button)event.getSource(), lblPuntaje);
        
        Timer tiempo = new Timer();
        tiempo.schedule(new TimerTask() {
            @Override
            public void run() { Platform.runLater(() -> {
                    //Muestra siguiente pregunta
                    mostrarPregunta(lblPregunta, numeroPregunta);
                    ((Button)event.getSource()).setDisable(false);
                });}}, valorPausa);
    }
    
    void verificarCorrecta(Button b, Label lblPuntaje){
        if((mapaBotonesRespuesta.get(b).esCorrecta == true)){        
            puntaje += 10;
            lblPuntaje.setText("Puntaje: " + puntaje);
            preguntasCorrectas += 1;
            contadorPregunta ++;
        }
        if((mapaBotonesRespuesta.get(b).esCorrecta == false)){
            verificarSeguro(preguntasCorrectas);
        }   
    }
    
    void verificarSeguro(int correctas){
        if (correctas >= 8){ puntaje = 80; aprobado(); alertaPuntaje();}
        if (correctas >= 6){ puntaje = 60; aprobado(); alertaPuntaje();}
        else{ puntaje = 0; reprobado(); alertaPuntaje();}
    }
    
    void aprobado(){
        statusAprobado = true;
    }
    
    void reprobado(){
        statusAprobado = false;
    }
    
    String imprimirStatusAprobacion(){
        if (statusAprobado == true){
            return "Aprobado";
        }
        if (statusAprobado == false){
            return "Reprobado.";
        }
       return null;    
    }

    void alertaPuntaje(){
        Alert fin = new Alert(Alert.AlertType.INFORMATION);
        fin.setTitle("Juego terminado.");
        fin.setHeaderText("Puntaje: " + puntaje);
        fin.setContentText("Preguntas correctas: " + preguntasCorrectas + " de 10. "
                + imprimirStatusAprobacion());
        fin.showAndWait();
        
        //Termina de ejecutar
        Platform.exit();
        System.exit(0);
    }
    
    //TODO: Crear metodo para comodin 50/50
    void comodin50(){
        for (int i = 0; i < 2; i++){        
            for (Button btnRespuesta: mapaBotonesRespuesta.keySet()){
                if((mapaBotonesRespuesta.get(btnRespuesta)).esCorrecta == false){
                    btnRespuesta.setDisable(true);
                }            
            }
        }
    }
    //TODO: Crear metodo para comodin de Pregunta al Publico
    void comodinPAP(){}
    
    /**
     * Getter requerido para transicion a escena JuegoPane
     * @return
     */
    public BorderPane getRoot() {
        return root;
    }

    /**
     * @return the lblPregunta
     */
    public Label getLblPregunta() {
        return lblPregunta;
    }

    /**
     * @return the numeroPregunta
     */
    public Label getNumeroPregunta() {
        return numeroPregunta;
    }
    
}
