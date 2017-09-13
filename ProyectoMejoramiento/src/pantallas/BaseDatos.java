/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import static aplicacion.Principal.sPrimario;
import datos.Almacenamiento;
import datos.Pregunta;
import datos.Respuesta;
import java.util.HashMap;
import javafx.scene.Scene;
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

/*
*/
public class BaseDatos {
    BorderPane root;
    VBox box;
    TextArea txtRespuestas;
    ChoiceBox cbPreguntas;
    Button volver;
    
    /**
     *Esta pantalla nos muestra las preguntas y respuestas agregadas;
     *Sirve en cierto modo, para poder probar el funcionamiento contestando cantidades
     *distintas de preguntas (testeando los puntos seguros).
     */
    public BaseDatos(){
        //Inicializa elementos del panel BaseDatos
        root = new BorderPane();
        box = new VBox();        
        box.setPrefWidth(400);
        txtRespuestas = new TextArea();
        volver = new Button("Volver a menu");
        
        //Crea una caja de opciones con las preguntas almacenadas
        cbPreguntas = new ChoiceBox();        
        cbPreguntas.getItems().addAll(Almacenamiento.getPreguntas());
        
        //Agrega (y de una vez otorga alineacion) a nuestros nodos
        root.setTop(cbPreguntas);
        root.setRight(txtRespuestas);
        root.setCenter(box);
        root.setBottom(volver);
        
        //Evento que maneja la ChoiceBox
        cbPreguntas.setOnMouseClicked(MouseEvent -> clicChoice());
        //Evento de transicion a Menu
        volver.setOnMouseClicked(MouseEvent ->
                sPrimario.setScene(new Scene(new Menu().getRoot())));
    }

    /**
     * Getter requerido para transicion a la escena BaseDatos.
     * @return
     */
    public BorderPane getRoot() {
        return root;
    }
    
    void clicChoice(){
        //Despeja la caja, para sólo mostrar botones correspondientes a pregunta escogida
        box.getChildren().clear();
        //Recopila datos requeridos para la escena BaseDatos
        HashMap <Button, Respuesta> mapaBR = new HashMap();
        
        //Para selección de items de la ChoiceBox
        if (cbPreguntas.getSelectionModel().getSelectedItem()!= null){
            Pregunta x = (Pregunta)cbPreguntas.getSelectionModel().getSelectedItem();

        //Para automatizar la creación de botones de respuestas de cada preguntas        
        for(Respuesta respuesta: Almacenamiento.mapaPR.get(x)){
            Button btnRespuesta = new Button(respuesta.toString());
            box.getChildren().add(btnRespuesta);
            if (!mapaBR.containsKey(btnRespuesta) && !mapaBR.containsValue(respuesta)){
                mapaBR.put(btnRespuesta, respuesta);}}
        
        //Evento para mostrar respuestas con su valor de verdad
        for(Button btnRespuesta: mapaBR.keySet()){
            btnRespuesta.setOnMouseClicked((MouseEvent MouseEvent) -> {
                txtRespuestas.clear();
                txtRespuestas.appendText((mapaBR.get(btnRespuesta)).toString() +
                        "  -> Valor de verdad: " + (mapaBR.get(btnRespuesta)).esCorrecta);
            });}    
        }    
    }
}