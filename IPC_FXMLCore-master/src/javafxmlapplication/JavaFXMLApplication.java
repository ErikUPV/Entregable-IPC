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
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;


public class JavaFXMLApplication extends Application {
    private static HashMap<Paginas, Parent> roots = new HashMap<>();
    
    private static Scene scene; 
    
    private Club club;
    
    private static Member member;
    
    private static BooleanProperty updated;

    
    
    public static Member getLoggedMember() {
        return member;
    }
    
    public static void setLoggedMember(Member m){
        member = m;
    }
    
    public static void setRoot(Parent root) {
        scene.setRoot(root);
        
    }
    
    
    
  
    public static void setRoot(Paginas clave){
        scene.setRoot(roots.get(clave));
    }
    @Override
    public void start(Stage stage) throws Exception {
        club = Club.getInstance();
        club.setInitialData();
        club.addSimpleData();
        
        updated = new SimpleBooleanProperty(false);
        
        
       
        System.out.println();
        //======================================================================
        Parent root;
        FXMLLoader loader;
        // 1- creación del grafo de escena a partir del fichero FXML
        
        loader= new  FXMLLoader(getClass().getResource("inicio/FXMLDocument.fxml"));
        root = loader.load();
        roots.put(Paginas.INICIO, root);
        
        loader = new FXMLLoader(getClass().getResource("autenticacion/FXMLAutenticacion.fxml"));
        root = loader.load();
        roots.put(Paginas.AUTENTICACION, root);
        loader = new FXMLLoader(getClass().getResource("registro/FXMLRegistro.fxml"));
        root = loader.load();
        roots.put(Paginas.REGISTRO, root);
        loader = new FXMLLoader(getClass().getResource("registro/FXMLRegistro_1.fxml"));
        root = loader.load();
        roots.put(Paginas.REGISTRO2, root);
        loader = new FXMLLoader(getClass().getResource("pistas/FXMLVerPistas.fxml"));
        root = loader.load();
        roots.put(Paginas.PISTAS, root);
        loader = new FXMLLoader(getClass().getResource("espacio_personal/FXMLEspacioPersonal.fxml"));
        root = loader.load();
        roots.put(Paginas.ESPACIO_PERSONAL, root);
        loader = new FXMLLoader(getClass().getResource("espacio_personal/FXMLEspacioP.fxml"));
        root = loader.load();
        roots.put(Paginas.ESPACIO_P, root);
        loader = new FXMLLoader(getClass().getResource("espacio_personal/FXMLModificarD.fxml"));
        root = loader.load();
        roots.put(Paginas.MODIFICAR_D, root);
        loader = new FXMLLoader(getClass().getResource("inicio2/FXMLDocument.fxml"));
        root = loader.load();
        roots.put(Paginas.INICIO2, root);
        loader = new FXMLLoader(getClass().getResource("espacio_personal/FXMLModificarDatos.fxml"));
        root = loader.load();
        roots.put(Paginas.MODIFICAR_DATOS, root);
        loader = new FXMLLoader(getClass().getResource("pistas/FXMLPistaConcreta.fxml"));
        root = loader.load();
        roots.put(Paginas.PISTA_CONCRETA, root);
        
        
        
        scene = new Scene(root);
        
        Image img = new Image(new FileInputStream("src\\javafxmlapplication\\imagenes\\tennis.png"));
        
                setRoot(Paginas.INICIO);

        
        String css;
        css = this.getClass().getResource("estilos.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setMinWidth(830);
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
    
    public static BooleanProperty updatedProperty(){
        return updated;
    }


    
}
