/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Club;


public class JavaFXMLApplication extends Application {
    private static HashMap<Paginas, FXMLLoader> roots = new HashMap<>();
    
    private static Scene scene; 
    
    private Club club;
    
    Parent lastRoot;
    
    public static void getLastRoot() {
        
    }
    
    public static void setRoot(Parent root) {
        scene.setRoot(root);
        
    }
    
  
    public static FXMLLoader getLoader(Paginas clave){
        return roots.get(clave);
    }
    @Override
    public void start(Stage stage) throws Exception {
        club = Club.getInstance();
        club.setInitialData();
       
        //======================================================================
        Parent root;
        FXMLLoader loader;
        // 1- creación del grafo de escena a partir del fichero FXML
        
        loader= new  FXMLLoader(getClass().getResource("inicio/FXMLDocument.fxml"));
        root = loader.load();
        roots.put(Paginas.INICIO, loader);
        scene = new Scene(root);
        loader = new FXMLLoader(getClass().getResource("autenticacion/FXMLAutenticacion.fxml"));
        
        roots.put(Paginas.AUTENTICACION, loader);
        loader = new FXMLLoader(getClass().getResource("registro/FXMLRegistro.fxml"));
        roots.put(Paginas.REGISTRO, loader);
        loader = new FXMLLoader(getClass().getResource("registro/FXMLRegistro_1.fxml"));
        roots.put(Paginas.REGISTRO2, loader);
        loader = new FXMLLoader(getClass().getResource("pistas/FXMLVerPistas.fxml"));
        roots.put(Paginas.PISTAS, loader);
        loader = new FXMLLoader(getClass().getResource("espacio_personal/FXMLEspacioPersonal.fxml"));
        roots.put(Paginas.ESPACIO_PERSONAL, loader);
        loader = new FXMLLoader(getClass().getResource("inicio2/FXMLDocument.fxml"));
        roots.put(Paginas.INICIO2, loader);
        loader = new FXMLLoader(getClass().getResource("espacio_personal/FXMLModificarDatos.fxml"));
        roots.put(Paginas.MODIFICAR_DATOS, loader);
        loader = new FXMLLoader(getClass().getResource("pistas/FXMLPistaConcreta.fxml"));
        roots.put(Paginas.PISTA_CONCRETA, loader);
        
        
        Image img = new Image(new FileInputStream("src\\javafxmlapplication\\imagenes\\tennis.png"));
        
        setRoot(root);
        
        String css;
        css = this.getClass().getResource("estilos.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setMinWidth(800);
        stage.setMinHeight(650);
        stage.getIcons().add(img);
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
