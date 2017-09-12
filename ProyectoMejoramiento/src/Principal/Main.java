/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Juandi
 */
public class Main extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // This loads the FXML files for the GUI
        Parent root = FXMLLoader.load(getClass().getResource("quizWindow.fxml"));                
        // Loads the CSS for the FXML
        root.getStylesheets().add("Principal/lightTheme.css");
        primaryStage.setResizable(false);
        primaryStage.setTitle("Quien quiere pasar POO?");
        primaryStage.setOnCloseRequest((event -> {
  	// Terminates the scheduled TimerTask to display next question
            Platform.exit();
            System.exit(0);
        }));
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }  
    
    public static void main(String[] args) {
        launch(args);        
    }
    
}
