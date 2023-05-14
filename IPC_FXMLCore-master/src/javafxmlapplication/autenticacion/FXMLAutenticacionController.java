/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.autenticacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import model.*;

/**
 * FXML Controller class
 *
 * @author Héctor
 */
public class FXMLAutenticacionController implements Initializable {

    @FXML
    private VBox vBoxPrincipal;
    @FXML
    private BorderPane borderPane;
    
    private Club club;
    @FXML
    private Button loginButton;
    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField passwordField;
    
    private Member member;
    @FXML
    private Label debugLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            club = Club.getInstance();
            club.registerMember("nombre", "apellido", "999999999", "admin", "admin", "51555555555", 333, null);

        } catch ( ClubDAOException | IOException e) {
            System.err.println("Error hallado: " + e);
        }
        
        debugLabel.setText(""+ club.getCourts().toString());
        
        // TODO
        borderPane.widthProperty().addListener(
                (observable, oldV, newV) -> {
                    if (newV.intValue() < 1000) vBoxPrincipal.maxWidthProperty().bind(borderPane.widthProperty().multiply(0.35));
                    else vBoxPrincipal.maxWidthProperty().bind(borderPane.widthProperty().multiply(0.3));
                });
    }    

    @FXML
    private void backButtonOnAction(ActionEvent event) {
        JavaFXMLApplication.setRoot(Paginas.INICIO);
        
    }

    @FXML
    private void loginButtonOnAction(ActionEvent event) throws InterruptedException {
        try {
            member = club.getMemberByCredentials(userTextField.getText(), passwordField.getText());
             if ( member != null){
            debugLabel.textProperty().set("Bienvenido " + member.getNickName());
        } 
        } catch (NullPointerException e) {
            debugLabel.setText("Por favor introduzca unas credenciales válidas");
         
        }
        
       
        
        
       
    
        }
    
}
