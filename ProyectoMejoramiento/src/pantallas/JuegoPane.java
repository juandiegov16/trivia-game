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
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javafx.geometry.Pos;
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
   static Random rand;
   static int puntaje = 0;
   static int contadorPregunta;
   static int indicePregunta = 0;
   static int preguntasCorrectas = 0;
   private boolean puntoSeguro;
   /**
    * @return the indicePregunta
    */
   public static int getIndicePregunta() {
       return indicePregunta;
   }

   //Elementos de JavaFX
   BorderPane root;
   public Label lblPregunta;
   public Label numeroPregunta;
   Button btn50, btnPAP; 
   HBox comodines;
   VBox box;       

   List<Pregunta> pregsJuego;
       
              
    /**
     * Pantalla de juego.
     */
    public JuegoPane(){
        this.pregsJuego = new ArrayList<>(Almacenamiento.getPreguntas());
        root = new BorderPane();
        root.setPrefSize(600,200);
        lblPregunta = new Label();
        numeroPregunta = new Label();
        btn50 = new Button("50/50");
        btnPAP = new Button("Preguntar al Público");
        comodines = new HBox();
        
        root.setTop(lblPregunta);
        lblPregunta.setAlignment(Pos.CENTER);
        box = new VBox();        
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);                
        root.setCenter(box);
        comodines.getChildren().addAll(btn50, btnPAP, numeroPregunta);
        comodines.setSpacing(10);
        root.setBottom(comodines);
        
        mostrarPregunta(lblPregunta, numeroPregunta);
        
    }   
    
    public void mostrarPregunta(Label lblPregunta, Label lblCorrecta){
        box.getChildren().clear();
        HashMap <Button, Respuesta> mapaBotonesRespuesta = new HashMap();
//        System.out.println(pregsJuego); //Impresión de prueba del ArrayList
        Pregunta pregunta = pregsJuego.get(new Random().nextInt(pregsJuego.size()));
        
        for(Respuesta respuesta: Almacenamiento.mapaPR.get(pregunta)){
            Button btnRespuesta = new Button(respuesta.toString());
            //btnRespuesta.setPrefWidth(Double.MAX_VALUE);
            box.getChildren().add(btnRespuesta);
            if (!mapaBotonesRespuesta.containsKey(btnRespuesta) && !mapaBotonesRespuesta.containsValue(respuesta)){
                mapaBotonesRespuesta.put(btnRespuesta, respuesta);}}
        
        lblPregunta.setText(pregunta.toString());
        lblCorrecta.setText("Pregunta " + (contadorPregunta + 1));        
            
    }
    
    //TODO: Crear metodo para verificar preguntas y respuestas.
    void verificarRespuestaCorrecta(){}
    //TODO: Crear metodo para determinar el puntaje y lidiar con los puntos seguros.
    //TODO: Crear metodo para comodin 50/50
    void comodin50_50(){}
    //TODO: Crear metodo para comodin de Pregunta al Publico
    void comodinPreguntarAlPublico(){}

    /**
     * Getter requerido para transicion a escena JuegoPane
     * @return
     */

    public BorderPane getRoot() {
        return root;
    }

    /**
     * @return the puntoSeguro
     */
    public boolean isPuntoSeguro() {
        return puntoSeguro;
    }

    /**
     * @param puntoSeguro the puntoSeguro to set
     */
    public void setPuntoSeguro(boolean puntoSeguro) {
        this.puntoSeguro = puntoSeguro;
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
