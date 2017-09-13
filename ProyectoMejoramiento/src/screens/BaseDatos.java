/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import data.Almacenamiento;
import data.Pregunta;
import data.Respuesta;
import java.util.HashMap;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Juandi
 */
public class BaseDatos {
    BorderPane root;
    VBox box;
    TextArea txtRespuestas;
    ChoiceBox cbPreguntas;
    
    public BaseDatos(){
        root = new BorderPane();
        box = new VBox();        
        box.setPrefWidth(300);
        txtRespuestas = new TextArea();
        
        cbPreguntas = new ChoiceBox();        
        cbPreguntas.getItems().addAll(Almacenamiento.getPreguntas());
        
        root.setTop(cbPreguntas);
        root.setRight(txtRespuestas);
        root.setCenter(box);
        
        cbPreguntas.setOnMouseClicked(MouseEvent -> clicChoice());
    }
    public BorderPane getRoot() {
        return root;
    }
    
    void clicChoice(){
            box.getChildren().clear();
            HashMap <Button, Respuesta> mapaBR = new HashMap();
            
            if (cbPreguntas.getSelectionModel().getSelectedItem()!= null){
                Pregunta x = (Pregunta)cbPreguntas.getSelectionModel().getSelectedItem();
            
            
            for(Respuesta respuesta: Almacenamiento.getMapaPR().get(x)){
                Button btnRespuesta = new Button(respuesta.toString());
                box.getChildren().add(btnRespuesta);
                if (!mapaBR.containsKey(btnRespuesta) && !mapaBR.containsValue(respuesta)){
                    mapaBR.put(btnRespuesta, respuesta);}
            }
            
            for(Button btnRespuesta: mapaBR.keySet()){
                btnRespuesta.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent MouseEvent) {
                        txtRespuestas.clear();
                        txtRespuestas.appendText((mapaBR.get(btnRespuesta)).toString() +
                                "  -> Valor de verdad: " + (mapaBR.get(btnRespuesta)).esCorrecta);
                    }
                });
            }    
        }    
    }
}