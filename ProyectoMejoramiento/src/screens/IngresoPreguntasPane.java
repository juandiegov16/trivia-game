/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;


import application.Principal;
import static application.Principal.sPrimario;
import data.Almacenamiento;
import data.Pregunta;
import data.Respuesta;
import java.util.HashSet;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    VBox root;
    Label lblPregunta, lblRespuestas;
    TextField txtPregunta;
    TextArea txtRespuestas;
    Button btnIngresar, btnSiguiente, btnVolver;
    
    public IngresoPreguntasPane(){
        root = new VBox();
        lblPregunta = new Label("Pregunta");
        txtPregunta = new TextField();
        lblRespuestas = new Label("Respuestas - Correcta en primera línea/"
                + "1 por línea/Mínimo 2, máximo 5.");        
        txtRespuestas = new TextArea();
        btnIngresar = new Button("Ingresar Pregunta");
        btnSiguiente = new Button("Ir a Base de Datos");
        btnVolver = new Button("Volver a menu");
        
        btnIngresar.setOnMouseClicked(MouseEvent -> clicBtnIngresar());
        btnSiguiente.setOnMouseClicked(MouseEvent -> clicBtnSiguiente());
        btnVolver.setOnMouseClicked(MouseEvent ->
                sPrimario.setScene(new Scene(new Menu().getRoot())));
    
        root.getChildren().addAll(lblPregunta, txtPregunta, lblRespuestas,
                txtRespuestas, btnIngresar, btnSiguiente, btnVolver);
    }
    
    void clicBtnIngresar(){
        String[] line = txtRespuestas.getText().split("\n");
        
        HashSet <Respuesta> respuestas = new HashSet();
        for (int i = 0; i < line.length; i++){
            if (i == 0){
                Respuesta respuestaC = new Respuesta(line[i], true);
                respuestas.add(respuestaC);            
            }
            else{
                Respuesta respuestaI = new Respuesta(line[i], false);
                respuestas.add(respuestaI);                
            }       
        }
        Almacenamiento.getPreguntas().add(new Pregunta(txtPregunta.getText()));
        Almacenamiento.getMapaPR().put(new Pregunta(txtPregunta.getText()), respuestas);
    }
    
    void clicBtnSiguiente(){
        Scene s = new Scene(new BaseDatos().getRoot());
        Principal.sPrimario.setScene(s);
    }
    
    

    public VBox getRoot() {
        return root;
    }
    
    
}
