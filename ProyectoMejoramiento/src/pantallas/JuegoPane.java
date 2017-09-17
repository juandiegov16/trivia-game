/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import datos.Almacenamiento;
import datos.Pregunta;
import datos.Respuesta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import static pantallas.RegistroPane.guardar;

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
   static String jugador;   
   
   //Elementos de JavaFX
   BorderPane root;

    /**
     * (Estético)
     */
    public Label lblPregunta;

    /**
     * Bastante obvio; da el numero de la pregunta actual.
     */
    public Label numeroPregunta;
   Label lblPuntaje;
   Button btn50, btnPAP; 
   HBox comodines;
   VBox box;       

   ArrayList<Pregunta> pregsJuego;

    /**
     * HashMap de Botones con sus respectivos contenidos (respuestas);
     * cambia para cada pregunta
     */
    public HashMap <Button, Respuesta> mapaBotonesRespuesta;
   int cantidadPreguntas;
   static int valorPausa = 1500;
   int contadorSeg = 5;
              
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
        btnPAP.setOnAction(e -> comodinPAP());
        
        comodines.getChildren().addAll(btn50, btnPAP, numeroPregunta, lblPuntaje);
        comodines.setSpacing(10);
        root.setBottom(comodines);
        
       try {       
           mostrarPregunta(lblPregunta, numeroPregunta);
       } catch (IOException ex) {
           Logger.getLogger(JuegoPane.class.getName()).log(Level.SEVERE, null, ex);
       }
    }   
    
    /**
     * Muestra la pregunta a contestar;
     * cambia el contenido de las etiquetas lblPregunta y numeroPregunta
     * @param lblPregunta
     * @param lblCorrecta
     * @throws java.io.IOException
     */
    public void mostrarPregunta(Label lblPregunta, Label lblCorrecta) throws IOException{
        box.getChildren().clear();
        mapaBotonesRespuesta.clear();
//        System.out.println(pregsJuego); //Impresión de prueba del ArrayList
        int numero = new Random().nextInt(cantidadPreguntas);
                Pregunta pregunta = pregsJuego.get(new Random().nextInt(cantidadPreguntas));
                //System.out.println(pregunta); //Impresión de prueba
                pregsJuego.remove(pregunta);
                cantidadPreguntas --;
                //System.out.println(cantidadPreguntas);
                
                if (preguntasCorrectas == 10){
                    puntaje = 100; aprobado(); alertaPuntaje();
                }
                
                //Creación dinámica de botones para las respuestas
                for(Respuesta respuesta: Almacenamiento.mapaPR.get(pregunta)){
                    Button btnRespuesta = new Button(respuesta.toString());
                    box.getChildren().add(btnRespuesta);
                    btnRespuesta.setOnAction((event) -> {
                        try {
                            this.comportamientoBoton(event);
                        } catch (IOException ex) {
                            Logger.getLogger(JuegoPane.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    if (!mapaBotonesRespuesta.containsKey(btnRespuesta) && !mapaBotonesRespuesta.containsValue(respuesta)){
                        mapaBotonesRespuesta.put(btnRespuesta, respuesta);}}
                
                //Texto de referencia para la pregunta actual
                lblPregunta.setText(pregunta.toString());
                lblCorrecta.setText("Pregunta " + (contadorPregunta));            
    }
    
    private void comportamientoBoton(ActionEvent event) throws IOException{
        ((Node)event.getSource()).setDisable(true);
        verificarCorrecta((Button)event.getSource(), lblPuntaje);
        
        Timer tiempo = new Timer();
        tiempo.schedule(new TimerTask() {
            @Override
            public void run() { Platform.runLater(() -> {
                try {
                    //Muestra siguiente pregunta
                    mostrarPregunta(lblPregunta, numeroPregunta);
                } catch (IOException ex) {
                    Logger.getLogger(JuegoPane.class.getName()).log(Level.SEVERE, null, ex);
                }
                    ((Button)event.getSource()).setDisable(false);
                });}}, valorPausa);
    }
    
    void verificarCorrecta(Button b, Label lblPuntaje) throws IOException{
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
    
    void verificarSeguro(int correctas) throws IOException{
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
            return " Aprobado";
        }
        if (statusAprobado == false){
            return " Reprobado.";
        }
       return null;    
    }

    void alertaPuntaje() throws IOException{
        Alert fin = new Alert(Alert.AlertType.INFORMATION);
        fin.setTitle(jugador + ", su juego ha terminado.");
        fin.setHeaderText("Puntaje: " + puntaje);
        fin.setContentText("Preguntas correctas: " + preguntasCorrectas + " de 10. "
                + imprimirStatusAprobacion());
        //Puntaje y usuario guardados en archivo
        guardar();
        fin.showAndWait();
        
        //Termina de ejecutar
        Platform.exit();
        System.exit(0);
    }
    
    // Elimina dos respuestas incorrectas al azar.    
    void comodin50(){
        int contadorComodin = 0;              
            for (Button btnRespuesta: mapaBotonesRespuesta.keySet()){
                if((mapaBotonesRespuesta.get(btnRespuesta)).esCorrecta == false){
                    btnRespuesta.setDisable(true);
                    contadorComodin++;                    
                    if (contadorComodin == 2){                        
                        btn50.setDisable(true);
                        break;
                    }
                }            
            }        
    }
    
    /* Muestra 3 respuestas al azar, que pueden o no incluir la respuesta correcta.
    Asigna un porcentaje a cada respuesta (entre todos suman 100%).
    Todo lo anterior será visible por 5 solamente 5 segundos.    
    */
    void comodinPAP() {
        int contadorPAP = 0;
        Alert altPAP = new Alert(Alert.AlertType.INFORMATION);        
        altPAP.setTitle("El publico opina: ");
        StringBuilder stringBuilder = new StringBuilder();
        Random r = new Random();
        Integer temp = 0;
        Integer suma = 0;
        ArrayList<Float> numerosRandom = new ArrayList<>();
        
        //Crea los porcentajes para cada respuesta, asegurándose que sumen 100
        //y sean valores positivos.
        for (int i = 1; i<= 3; i++){
            if (!(i == 3)){
                temp = r.nextInt((100 - suma) / (3 - i)) + 1;
                numerosRandom.add(temp.floatValue());
                suma += temp;            
            }
            else{
                Integer ultimo = (100 - suma);
                numerosRandom.add(ultimo.floatValue());
                suma += ultimo;            
            }        
        }        
        
        //Crea el texto con las respuestas para la alerta del comodín
        for(Button btnRespuesta: mapaBotonesRespuesta.keySet()){                
            Label lblResp = new Label(mapaBotonesRespuesta.get(btnRespuesta).toString()
                    + " " + ((numerosRandom.get(contadorPAP))) + "%");
            stringBuilder.append(lblResp.getText()).append("\n");
            contadorPAP++;
            
            //Asigna el texto y deshabilita el btnPAP
            if (contadorPAP == 3){
                altPAP.setContentText(stringBuilder.toString());
                btnPAP.setDisable(true);
                break;                
            }
        }
        
        //Muestra la alerta.
        //Nótese que al usar show() en vez de showAndWait(), se puede
        //implementar un cierre temporizado, como se requiere.
        altPAP.show();           
            
            //Muestra la cuenta regresiva del comodín Preguntar al Público.
            //Cierra la respectiva alerta cuando se acaba el tiempo.
            Thread cuentaAtras = new Thread(()->{                
                while (contadorSeg > 0){
                    try{
                        Platform.runLater(() -> {
                            altPAP.setHeaderText("Cerrando en " + contadorSeg + " segundo(s)...");
                        });
                        contadorSeg--;       
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JuegoPane.class.getName()).log(Level.SEVERE, null, ex);
                    }                
                }
                Platform.runLater(()-> {
                    altPAP.close();               
                });
            });
        cuentaAtras.start();            
        }
    
        
    /**
     * Getter requerido para transicion a escena JuegoPane
     * @return
     */
    public BorderPane getRoot() {
        return root;
    }    
}
