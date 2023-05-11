/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.inicio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafxmlapplication.JavaFXMLApplication;

/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    private Label labelMessage;
    private ImageView fondoImageView;
    private Pane loginPane;
    @FXML
    private VBox loginVBOX;
    @FXML
    private StackPane loginStackpane;
    
    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
   
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        
//        BackgroundImage myBI= new BackgroundImage(new Image("/imagenes/pexels-pixabay-209977.jpg",32,32,false,true),
//        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//        BackgroundSize.DEFAULT);
////then you set to your node
//        loginStackpane.setBackground(new Background(myBI));
       
        
    }    

    @FXML
    private void IniciarSesionOnAction(ActionEvent event) {
        JavaFXMLApplication.setRoot("autenticacion");
    }
    
}
