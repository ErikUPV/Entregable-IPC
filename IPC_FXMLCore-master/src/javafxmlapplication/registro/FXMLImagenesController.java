/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.registro;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Héctor
 */
public class FXMLImagenesController implements Initializable {

    @FXML
    private ToggleGroup imagen;
    @FXML
    private ToggleButton elegirImagen;
    @FXML
    private ImageView imagenPersonalizada;
    @FXML
    private Button aceptarButton;
    @FXML
    private Button cancelarButton;
    private boolean pulsadoOK = false;
    private Image imagenFinal = null;
    ToggleButton dumb;

    /**
     * Initializes the controller class.
     */
    
    public boolean isOKPressed() {
        return pulsadoOK;
    }
    
    public Image getImage() {
        return imagenFinal;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dumb = new ToggleButton();
        imagen.getToggles().add(dumb);
        dumb.setSelected(true);
        dumb.setVisible(false);
    }    

    @FXML
    private void elegirImagenOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elegir imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(
                ((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            Path imgPath = selectedFile.toPath();
            Image img = new Image(imgPath.toString());
            elegirImagen.setText("");
            imagenPersonalizada.setFitWidth(120);
            imagenPersonalizada.setImage(img);
            imagenFinal = img;
        }
    }

    @FXML
    private void aceptarButtonOnAction(ActionEvent event) {
        if(dumb.isSelected()) {
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("No se ha seleccionado una imagen");
            a.setContentText("Al no seleccionar una imagen se usará una imagen por defecto");
            startAlert(a);
            Optional<ButtonType> result = a.showAndWait();
            
            if(result.get() == ButtonType.OK) {
                imagenFinal = new Image("/javafxmlapplication/imagenes/avatars/default.png");
            }
        } 
        pulsadoOK = true;
        aceptarButton.getScene().getWindow().hide();
    }

    @FXML
    private void cancelarButtonOnAction(ActionEvent event) {
        aceptarButton.getScene().getWindow().hide();
    }

    @FXML
    private void man1(ActionEvent event) {
        imagenFinal = new Image("/javafxmlapplication/imagenes/avatars/men.PNG");
    }

    @FXML
    private void man2(ActionEvent event) {
        imagenFinal = new Image("/javafxmlapplication/imagenes/avatars/men2.PNG");
    }

    @FXML
    private void man3(ActionEvent event) {
        imagenFinal = new Image("/javafxmlapplication/imagenes/avatars/men3.PNG");
    }

    @FXML
    private void man4(ActionEvent event) {
        imagenFinal = new Image("/javafxmlapplication/imagenes/avatars/men4.PNG");
    }

    @FXML
    private void man5(ActionEvent event) {
        imagenFinal = new Image("/javafxmlapplication/imagenes/avatars/men5.PNG");
    }

    @FXML
    private void woman1(ActionEvent event) {
        imagenFinal = new Image("/javafxmlapplication/imagenes/avatars/woman.PNG");
    }

    @FXML
    private void woman2(ActionEvent event) {
        imagenFinal = new Image("/javafxmlapplication/imagenes/avatars/woman2.PNG");
    }

    @FXML
    private void woman3(ActionEvent event) {
        imagenFinal = new Image("/javafxmlapplication/imagenes/avatars/woman3.PNG");
    }

    @FXML
    private void woman4(ActionEvent event) {
        imagenFinal = new Image("/javafxmlapplication/imagenes/avatars/woman4.PNG");
    }

    @FXML
    private void woman5(ActionEvent event) {
        imagenFinal = new Image("/javafxmlapplication/imagenes/avatars/woman5.PNG");
    }

    @FXML
    private void woman6(ActionEvent event) {
        imagenFinal = new Image("/javafxmlapplication/imagenes/avatars/woman6.PNG");
    }
    
    private void startAlert(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../estilos.css").toExternalForm());
        dialogPane.getStyleClass().add("myAlert");
    }
    
}
