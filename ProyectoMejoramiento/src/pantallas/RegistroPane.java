/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import static aplicacion.Principal.sPrimario;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Juandi
 */
public class RegistroPane{
    VBox root;
    Label lblJugador;
    TextField txtJugador;
    Button btnRegistrar, btnJugar;
    HBox boxBotones;
    
    
    /**
     * Permite ingresar y serializar usuarios/puntajes.
     */
    public RegistroPane(){
        root = new VBox();
        root.setSpacing(20);
        lblJugador = new Label("Ingrese su nombre: ");
        txtJugador = new TextField();
        btnRegistrar = new Button("Registrar");
        btnRegistrar.setOnAction(e -> verificarJugador());
        
        btnJugar = new Button("Jugar");
        btnJugar.setDisable(true);
        btnJugar.setOnAction(e -> {
            btnJugar.setDisable(true);
            sPrimario.setScene(new Scene(new JuegoPane().getRoot()));
        });
        
        boxBotones = new HBox();
        boxBotones.setSpacing(20);
        boxBotones.getChildren().addAll(btnRegistrar, btnJugar);
        
        root.getChildren().addAll(lblJugador, txtJugador, boxBotones);
    }
    
    void verificarJugador(){
        if(!(txtJugador.getText().length() > 0)){
            Alert altEJ = new Alert(Alert.AlertType.ERROR);
            altEJ.setTitle("Error de registro");
            altEJ.setHeaderText("Por favor escriba algo" + "\n"
                    + "e intente de nuevo.");
            altEJ.showAndWait();
        }
        else{
            Alert altSJ = new Alert(Alert.AlertType.INFORMATION);
            altSJ.setTitle("Registro exitoso");
            altSJ.setHeaderText("Se ha guardado su nombre." + "\n"
                    + "Puede jugar.");
            btnRegistrar.setDisable(true);
            btnJugar.setDisable(false);
            altSJ.showAndWait();
            JuegoPane.jugador = txtJugador.getText().toUpperCase();
            //System.out.println(JuegoPane.jugador);
        }   
    }
    
    
    //TODO: Crear metodo para guardar usuarios y puntajes.   
    public static void guardar() throws IOException{
        StringBuilder lineaJugador = new StringBuilder();
        lineaJugador.append("\n");
        lineaJugador.append(JuegoPane.jugador);
        lineaJugador.append("//").append(JuegoPane.puntaje);
        //System.out.println(lineaJugador);        
        
        PrintWriter output;
        output = new PrintWriter(new BufferedWriter(new FileWriter(("Jugadores.txt"), true)));
        output.println(lineaJugador);
        output.close();
    }
    
    
    /**
     * Getter requerido para transicion a escena RegistroPane.
     * @return
     */
    public VBox getRoot() {
        return root;
    }
    
    
    

}
