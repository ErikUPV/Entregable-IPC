/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.registro;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;

/**
 * FXML Controller class
 *
 * @author erikb
 */
public class FXMLRegistroController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private VBox vBoxPrincipal;
    @FXML
    private ColumnConstraints columnaPrincipal;
    @FXML
    private ImageView perfilImageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          borderPane.widthProperty().addListener(
                (observable, oldV, newV) -> {
                    if (newV.intValue() < 1000) columnaPrincipal.maxWidthProperty().bind(borderPane.widthProperty().multiply(0.35));
                    else columnaPrincipal.maxWidthProperty().bind(borderPane.widthProperty().multiply(0.3));
                });
          
    }    

    @FXML
    private void backButtonOnAction(ActionEvent event) {
        JavaFXMLApplication.setRoot(Paginas.INICIO);
        
    
    }

    @FXML
    private void elegirImagenOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elegir imagen");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        
        File selectedFile = fileChooser.showOpenDialog(
                ((Node) event.getSource()).getScene().getWindow());
             
        if (selectedFile != null) {
            Path imgPath = selectedFile.toPath();
            perfilImageView.setImage(new Image(imgPath.toString()));
        }
    }
   
    
}
