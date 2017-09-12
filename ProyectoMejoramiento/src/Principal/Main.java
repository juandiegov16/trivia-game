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
import javafx.scene.image.Image;
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
        
        // Adds the icon        
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

        // Loads the CSS for the FXML
        root.getStylesheets().add("application/lightTheme.css");
        primaryStage.setResizable(false);
        
  	// Did this to preserve "nice-lookingness" of the GUI
        primaryStage.setTitle("Gunvir's Quiz Game!");
        primaryStage.setOnCloseRequest((event -> {
  	// Terminates the scheduled TimerTask to display next question
            Platform.exit();
            System.exit(0);
        }));
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);        
    }
    
}
