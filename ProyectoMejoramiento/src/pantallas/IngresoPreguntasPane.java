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
        
        //Eventos para botones (transicion de escena)        
        btnIngresar.setOnMouseClicked(MouseEvent -> clicBtnIngresar());
        btnSiguiente.setOnMouseClicked(MouseEvent -> clicBtnSiguiente());
        btnVolver.setOnMouseClicked(MouseEvent ->
                sPrimario.setScene(new Scene(new Menu().getRoot())));
        
        //Adición de nodos al root de IngresoPreguntasPane            
        root.getChildren().addAll(lblPregunta, txtPregunta, lblRespuestas,
                txtRespuestas, btnIngresar, btnSiguiente, btnVolver);
    }
    
    void clicBtnIngresar(){
        //Devuelve un arreglo con las líneas del TextArea como Strings
        String[] line = txtRespuestas.getText().split("\n");
        
        //HashSet requerido previo agregación al mapa global
        HashSet <Respuesta> respuestas = new HashSet();
        
        //TODO: Validar numero max y min de respuestas.
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
