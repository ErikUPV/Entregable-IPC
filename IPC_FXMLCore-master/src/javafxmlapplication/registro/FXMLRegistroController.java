/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.registro;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import model.*;

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
    @FXML
    private TextField nickTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField tlfTextField;
    @FXML
    private PasswordField pwTextField;
    @FXML
    private PasswordField repeatPwTextField;
    @FXML
    private TextField creditCardTextField;
    @FXML
    private TextField cvcTextField;
    @FXML
    private Label badInputLabel;
    @FXML
    private Button registerButton;
    
    private Club club;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Listeners de los TextFields
        try {
            club = Club.getInstance();
        } catch (ClubDAOException | IOException e) {
            
        }
        
        
        
        
        
        columnaPrincipal.minWidthProperty().set(400);
        // TODO
          borderPane.widthProperty().addListener(
                (observable, oldV, newV) -> {
                    if (newV.intValue() <= 800) columnaPrincipal.maxWidthProperty().bind(columnaPrincipal.minWidthProperty());
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

    @FXML
    private void registerOnAction(ActionEvent event) {
       registerButton.disableProperty().setValue(true);
       if (nickTextField.textProperty().getValueSafe().isEmpty() || nickTextField.textProperty().getValueSafe().isBlank()) showErrorMessage(nickTextField);
       else hideErrorMessage(nickTextField);
       
       if (nameTextField.textProperty().getValueSafe().isEmpty() || nameTextField.textProperty().getValueSafe().) showErrorMessage( nameTextField);
       else hideErrorMessage(nameTextField);
       
       if (surnameTextField.textProperty().getValueSafe().isEmpty()) showErrorMessage( surnameTextField);
       else hideErrorMessage(surnameTextField);
       
       if (tlfTextField.textProperty().getValueSafe().isEmpty()) showErrorMessage( tlfTextField);
       else hideErrorMessage(tlfTextField);
       
       if (pwTextField.textProperty().getValueSafe().isEmpty()) showErrorMessage( pwTextField);
       else hideErrorMessage(pwTextField);
       
       if (repeatPwTextField.textProperty().getValueSafe().isEmpty()) showErrorMessage( repeatPwTextField);
       else hideErrorMessage(repeatPwTextField);
       
       if (creditCardTextField.textProperty().getValueSafe().isEmpty() || cvcTextField.textProperty().getValueSafe().isEmpty()){
           showErrorMessage( creditCardTextField);
           showErrorMessage( cvcTextField);

       } else{
           hideErrorMessage(creditCardTextField);
           hideErrorMessage(cvcTextField);
       }
       registerButton.setDisable(false);
       
       
       try {
           club.registerMember(nameTextField.textProperty().getValue(),surnameTextField.textProperty().getValue(), tlfTextField.textProperty().getValue(),
                   nickTextField.textProperty().getValue(), pwTextField.textProperty().getValue(), creditCardTextField.textProperty().getValue(), Integer.parseInt(cvcTextField.textProperty().getValue()), null);
       } catch (ClubDAOException e) {
           
       }

    }
   
    private void showErrorMessage ( TextField field) {
        String source = field.getId();
        field.requestFocus();
        badInputLabel.visibleProperty().setValue(true);
        field.styleProperty().setValue("-fx-background-color: #FCE5E0;"
                + "-fx-border-color: red;"
                + "-fx-border-radius: 5 5 5 5;"
                + "-fx-text-fill: red");
        switch(source) {
            case "nickTextField":
                nickTextField.setPromptText("");
        }
       
    }
    
     private void hideErrorMessage(TextField field){
        
        field.styleProperty().setValue(""); 
    }

    
 
    }
    

