/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Juandi
 */
public class Main extends Application{
    
    
    public static void main(String[] args) {
        launch(args);        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga el FXML
        Parent root = FXMLLoader.load(getClass().getResource("resources/quizWindow.fxml"));
        
        // Agrega el icono        
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        
        // Carga la CSS para el FXML
        root.getStylesheets().add("resources/lightTheme.css");
        primaryStage.setResizable(false);
        primaryStage.setTitle("Quien quiere pasar POO?");
        primaryStage.setOnCloseRequest((event -> {
            // Termina TimerTask para mostrar siguiente pregunta
            Platform.exit();
            System.exit(0);
        }));
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
}
