/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.pistas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import model.Court;

/**
 * FXML Controller class
 *
 * @author Héctor
 */
public class FXMLPistaConcretaController implements Initializable {

    @FXML
    private ImageView imagenPista;
    @FXML
    private Label title;
    

    /**
     * Initializes the controller class.
     */
    
    public void initPista(int pista) {
        title.setText("Reservar Pista " + pista);
        File f;
        switch(pista) {
            case 1:
                f = new File("src/javafxmlapplication/imagenes/courtImage-v1.png");
                break;
            case 2:
                f = new File("src/javafxmlapplication/imagenes/courtImage-v2.png");
                break;
            case 3:
                f = new File("src/javafxmlapplication/imagenes/courtImage-v3.png");
                break;
            case 4:
                f = new File("src/javafxmlapplication/imagenes/courtImage-v4.png");
                break;
            case 5:
                f = new File("src/javafxmlapplication/imagenes/courtImage-v5.png");
                break;
            default:
                f = new File("src/javafxmlapplication/imagenes/courtImage-v6.png");
                break;
        }
        Image i = new Image(f.toURI().toString());
        imagenPista.setImage(i);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }        

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTAS);
        Parent root = miCargador.getRoot();
        if (root == null) root = miCargador.load();
        JavaFXMLApplication.setRoot(root);
    }

}
