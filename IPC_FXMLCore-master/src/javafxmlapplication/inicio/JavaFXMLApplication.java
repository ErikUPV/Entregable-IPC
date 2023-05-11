/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.inicio;

import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class JavaFXMLApplication extends Application {
    private static HashMap<String, Parent> roots = new HashMap<>();
    
    private static Scene scene; 
    
    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }
    @Override
    public void start(Stage stage) throws Exception {
        //======================================================================
        // 1- creación del grafo de escena a partir del fichero FXML
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        
        
        Parent root = loader.load();
        
        scene = new Scene(root);
        
        String css;
        css = this.getClass().getResource("../estilos.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
        stage.setTitle("Green Ball");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }


    
}
