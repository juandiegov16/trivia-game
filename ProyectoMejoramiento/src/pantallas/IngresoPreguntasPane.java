/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;


import aplicacion.Principal;
import static aplicacion.Principal.sPrimario;
import datos.Almacenamiento;
import datos.Pregunta;
import datos.Respuesta;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author Juandi
 */
public class IngresoPreguntasPane {
    //Elementos del pane
    VBox root;
    Label lblPregunta, lblRespuestas;
    TextField txtPregunta;
    TextArea txtRespuestas;
    Button btnIngresar, btnSiguiente, btnVolver;
    
    /**
     *Permite el registro manual de preguntas/respuestas, valida cantidad de respuestas.
     */
    public IngresoPreguntasPane(){
        //Inicialización de elementos del Pane
        root = new VBox();
        lblPregunta = new Label("Pregunta");
        txtPregunta = new TextField();
        //Instrucciones para agregar Preguntas/Respuestas
        lblRespuestas = new Label("Respuestas - Correcta en primera línea/"
                + "1 por línea/Mínimo 2, máximo 5.");        
        txtRespuestas = new TextArea();
        btnIngresar = new Button("Ingresar Pregunta");
        btnSiguiente = new Button("Ir a Base de Datos");
        btnVolver = new Button("Volver a menu");
        
        //Ingresar la pregunta al documento y al juego.        
        btnIngresar.setOnMouseClicked(MouseEvent -> {
            try {
                clicBtnIngresar();
            } catch (IOException ex) {
                Logger.getLogger(IngresoPreguntasPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        //Eventos para botones (transicion de escena)
        btnSiguiente.setOnMouseClicked(MouseEvent -> clicBtnSiguiente());
        btnVolver.setOnMouseClicked(MouseEvent ->
                sPrimario.setScene(new Scene(new Menu().getRoot())));
        
        //Adición de nodos al root de IngresoPreguntasPane            
        root.getChildren().addAll(lblPregunta, txtPregunta, lblRespuestas,
                txtRespuestas, btnIngresar, btnSiguiente, btnVolver);
    }
    
    //Lee el TextField y el TextArea, para agregar la pregunta al juego.
    void clicBtnIngresar() throws IOException{
        //Devuelve un arreglo con las líneas del TextArea como Strings
        String[] line = txtRespuestas.getText().split("\n");        
        
        //Preparando String a agregar al archivo
        StringBuilder lineaArchivo = new StringBuilder();
        lineaArchivo.append("\n");
        lineaArchivo.append(txtPregunta.getText()).append(";");        
        for(String parte: line){
            lineaArchivo.append(parte);
            if(!parte.equals(line[line.length - 1])){
                lineaArchivo.append(";");
            }
        }
        
        //HashSet requerido previo agregación al mapa global
        HashSet <Respuesta> respuestas = new HashSet();        
        
        //Validación del mínimo y máximo de respuestas
        if(line.length < 2 || line.length > 5){
            Alert respError = new Alert(Alert.AlertType.ERROR);
            respError.setTitle("Error en ingreso de pregunta/respuestas.");
            respError.setHeaderText("Ha ingresado un número no válido de respuestas.");
            respError.setContentText("Vuelva a intentarlo, ingresando mínimo 2 y máximo 5.");
            respError.showAndWait();           
        }
        //Si el número de respuestas es válido, procede a agregar.
        else{
            for (int i = 0; i < line.length; i++){
                if (i == 0){
                    //Respuesta correcta en indice 0
                    Respuesta respuestaC = new Respuesta(line[i], true);
                    respuestas.add(respuestaC);            
                }
                else{
                    //Itera y agrega respuestas incorrectas a partir de indice 1
                    Respuesta respuestaI = new Respuesta(line[i], false);
                    respuestas.add(respuestaI);                
                }       
            }
            
        //Agrega exitosamente Preguntas y sets de Respuesta mediante ingreso manual
        Almacenamiento.getPreguntas().add(new Pregunta(txtPregunta.getText()));
        Almacenamiento.getMapaPR().put(new Pregunta(txtPregunta.getText()), respuestas);
        
        //System.out.println(lineaArchivo); //Impresión de prueba
        
        //Escribe la pregunta al archivo, en el formato especificado.
        PrintWriter output;
        output = new PrintWriter(new BufferedWriter(new FileWriter(("PreguntasJava1.txt"), true)));  //clears file every time
        output.println(lineaArchivo);
        output.close();
        
        //Alerta mostrando al usuario que ha ingresado bien
        Alert respExito = new Alert(Alert.AlertType.INFORMATION);
            respExito.setTitle("Éxito en ingreso de pregunta/respuestas.");
            respExito.setHeaderText("Pregunta ingresada correctamente.");
            respExito.setContentText("Ahora podrá utilizarla en el juego."
                    + " Para comprobar, puede Ir a Base de Datos");
            respExito.showAndWait();
            }
    }
    
    
    
    //Transición de escena a la pantalla Base de Datos
    //Para verificar lo agregado
    void clicBtnSiguiente(){
        Scene s = new Scene(new BaseDatos().getRoot());
        Principal.sPrimario.setScene(s);
    }

    /**
     * Getter requerido para transicion a escena IngresoPreguntasPane.
     * @return
     */
    public VBox getRoot() {
        return root;
    }
    
    
}
