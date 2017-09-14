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

   static ArrayList<Pregunta> pregsJuego;
   HashMap <Button, Respuesta> mapaBotonesRespuesta;
   ArrayList<Integer> listaIndices;
   ArrayList<Integer> listaSecundaria = new ArrayList<>();
   static int valorPausa = 1500;       
              
    /**
     * Pantalla de juego.
     */
    public JuegoPane(){
        JuegoPane.pregsJuego = new ArrayList<>(Almacenamiento.getPreguntas());
        mapaBotonesRespuesta = new HashMap();
        root = new BorderPane();
        root.setPrefSize(600,200);
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
        comodines.getChildren().addAll(btn50, btnPAP, numeroPregunta, lblPuntaje);
        comodines.setSpacing(10);
        root.setBottom(comodines);
        
        mostrarPregunta(lblPregunta, numeroPregunta);
        
        listaIndices = new ArrayList<>();        
        for (int i = 0; i < pregsJuego.size(); i++) {
            listaIndices.add(i);           
        }
        System.out.println(listaIndices); //Impresión de prueba (descomentar)        
    }   
    
    public void mostrarPregunta(Label lblPregunta, Label lblCorrecta){
        box.getChildren().clear();
        mapaBotonesRespuesta.clear();
//        System.out.println(pregsJuego); //Impresión de prueba del ArrayList
        int numero = new Random().nextInt((pregsJuego.size()));
            if(listaSecundaria.contains(numero)){
                numero = new Random().nextInt((pregsJuego.size()));
                listaSecundaria.add(numero);
                System.out.println(listaSecundaria);
                Pregunta pregunta = pregsJuego.get(numero);
                System.out.println(pregunta); //Impresión de prueba
                
                for(Respuesta respuesta: Almacenamiento.mapaPR.get(pregunta)){
                    Button btnRespuesta = new Button(respuesta.toString());
                    //btnRespuesta.setPrefWidth(Double.MAX_VALUE);
                    box.getChildren().add(btnRespuesta);
                    btnRespuesta.setOnAction((event) -> this.comportamientoBoton(event));
                    if (!mapaBotonesRespuesta.containsKey(btnRespuesta) && !mapaBotonesRespuesta.containsValue(respuesta)){
                        mapaBotonesRespuesta.put(btnRespuesta, respuesta);}}
                
                lblPregunta.setText(pregunta.toString());
                lblCorrecta.setText("Pregunta " + (contadorPregunta)); 
            }
            else{
                listaSecundaria.add(numero);
                System.out.println(listaSecundaria);
                Pregunta pregunta = pregsJuego.get(numero);
                System.out.println(pregunta); //Impresión de prueba
                
                for(Respuesta respuesta: Almacenamiento.mapaPR.get(pregunta)){
                    Button btnRespuesta = new Button(respuesta.toString());
                    //btnRespuesta.setPrefWidth(Double.MAX_VALUE);
                    box.getChildren().add(btnRespuesta);
                    btnRespuesta.setOnAction((event) -> this.comportamientoBoton(event));
                    if (!mapaBotonesRespuesta.containsKey(btnRespuesta) && !mapaBotonesRespuesta.containsValue(respuesta)){
                        mapaBotonesRespuesta.put(btnRespuesta, respuesta);}}
                
                lblPregunta.setText(pregunta.toString());
                lblCorrecta.setText("Pregunta " + (contadorPregunta));
            }
        
        
//        if ((listaSecundaria.contains(numero))){
//            numero = new Random().nextInt((pregsJuego.size()));
//            Pregunta pregunta = pregsJuego.get(numero);        
//            for(Respuesta respuesta: Almacenamiento.mapaPR.get(pregunta)){
//                Button btnRespuesta = new Button(respuesta.toString());
//                //btnRespuesta.setPrefWidth(Double.MAX_VALUE);
//                box.getChildren().add(btnRespuesta);
//                btnRespuesta.setOnAction((event) -> this.comportamientoBoton(event));
//                if (!mapaBotonesRespuesta.containsKey(btnRespuesta) && !mapaBotonesRespuesta.containsValue(respuesta)){
//                    mapaBotonesRespuesta.put(btnRespuesta, respuesta);}}
//
//            lblPregunta.setText(pregunta.toString());
//            lblCorrecta.setText("Pregunta " + (contadorPregunta));}        
        //else{
//          Pregunta pregunta = pregsJuego.get(numero);
//          System.out.println(pregunta); //Impresión de prueba
//            for(Respuesta respuesta: Almacenamiento.mapaPR.get(pregunta)){
//                Button btnRespuesta = new Button(respuesta.toString());
//                //btnRespuesta.setPrefWidth(Double.MAX_VALUE);
//                box.getChildren().add(btnRespuesta);
//                btnRespuesta.setOnAction((event) -> this.comportamientoBoton(event));
//                if (!mapaBotonesRespuesta.containsKey(btnRespuesta) && !mapaBotonesRespuesta.containsValue(respuesta)){
//                    mapaBotonesRespuesta.put(btnRespuesta, respuesta);}}
//
//            lblPregunta.setText(pregunta.toString());
//            lblCorrecta.setText("Pregunta " + (contadorPregunta)); 
        //}    
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
                if(preguntasCorrectas == pregsJuego.size()){
                    puntaje = (pregsJuego.size()*10);
                    alertaPuntaje();
                }
        }
        else{
            verificarSeguro(preguntasCorrectas);
        }   
    }
    
    void verificarSeguro(int correctas){
        if (correctas == 8){
            puntaje = 80;
            alertaPuntaje();
        }
        if (correctas == 6){
            puntaje = 60;
            alertaPuntaje();        
        }
        else{
            puntaje = 0;
            alertaPuntaje();
        }
    }    
    //TODO: Crear metodo para comodin 50/50
    void comodin50_50(){}
    //TODO: Crear metodo para comodin de Pregunta al Publico
    void comodinPreguntarAlPublico(){}
    
    void alertaPuntaje(){
        Alert fin = new Alert(Alert.AlertType.INFORMATION);
        fin.setTitle("Juego terminado.");
        fin.setHeaderText("Puntaje: " + puntaje);
        fin.setContentText("Preguntas correctas: " + preguntasCorrectas + " de " + pregsJuego.size());
        fin.showAndWait();
        
        //Termina de ejecutar
        Platform.exit();
        System.exit(0);
    }
    
    

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