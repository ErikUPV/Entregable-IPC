/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

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
    
    public static void setRoot(String clave) {
        Parent root = roots.get(clave);
        if (root != null) {
        setRoot(root);
        } else {
            System.out.println("No se pudo encontrar la escena");
        }
    }
    @Override
    public void start(Stage stage) throws Exception {
       
        //======================================================================
        Parent root;
        FXMLLoader loader;
        // 1- creación del grafo de escena a partir del fichero FXML
        loader= new  FXMLLoader(getClass().getResource("inicio/FXMLDocument.fxml"));
        root = loader.load();
        roots.put("inicio", root);
        scene = new Scene(root);
        loader = new FXMLLoader(getClass().getResource("autenticacion/FXMLAutenticacion.fxml"));
        root = loader.load();
        roots.put("autenticacion", root);
        
        
        setRoot("inicio");
        
        String css;
        css = this.getClass().getResource("estilos.css").toExternalForm();
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
