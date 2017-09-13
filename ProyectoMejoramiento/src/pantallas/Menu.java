/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import static aplicacion.Principal.sPrimario;
import datos.Almacenamiento;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author Juandi
 */
public class Menu {
    private VBox root;
    Button nuevoJuego, ingresoPreguntas, baseDeDatos;

    /**
     *Pantalla principal del juego, con opcion de jugar, registrar preguntas o
     *revisar la base de datos.
     */
    public Menu() {
        //Inicialización de elementos del pane Menu
        root = new VBox();
        nuevoJuego = new Button("Nuevo Juego");
        ingresoPreguntas = new Button("Ingreso de Preguntas");
        baseDeDatos = new Button("Base de Datos");
        
        //Sección estética
        //Ancho de botones
        nuevoJuego.setMaxWidth(Double.MAX_VALUE);
        ingresoPreguntas.setMaxWidth(Double.MAX_VALUE);
        baseDeDatos.setMaxWidth(Double.MAX_VALUE);        
        //Espaciado de botones
        root.setSpacing(20);
        
        //Eventos de transición de escena para botones del Menu
        nuevoJuego.setOnMouseClicked(e -> 
                sPrimario.setScene(new Scene(new RegistroPane().getRoot())));
        ingresoPreguntas.setOnMouseClicked(e -> 
                sPrimario.setScene(new Scene(new IngresoPreguntasPane().getRoot())));
        baseDeDatos.setOnMouseClicked(e -> 
                sPrimario.setScene(new Scene(new BaseDatos().getRoot())));
        
        //Impresión de prueba del mapa con la estructura Pregunta-Set de Respuestas
        //Descomentar para utilizar (NO remover el import de Almacenamiento)
//        baseDeDatos.setOnMouseClicked(e->
//                Almacenamiento.getMapaPR().forEach((k,v)
//                                -> System.out.println(k+", "+v)));
        
        //Adición de nodos al root
        root.getChildren().addAll(nuevoJuego, ingresoPreguntas,baseDeDatos);        
    }

    /**
     * @return the root
     */
    //Getter requerido
    public VBox getRoot() {
        return root;
    }
}
