/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.espacio_personal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.*;

/**
 * FXML Controller class
 *
 * @author erikb
 */
public class FXMLModificarDatosController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Button reservarPista;
    @FXML
    private Button misReservas;
    @FXML
    private Button modificarPerfil;
    @FXML
    private Label nameLabel;
    @FXML
    private VBox cambioVBOX;
    @FXML
    private Button cancelarButton;
    @FXML
    private Button aceptarButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField cardField;
    @FXML
    private TextField repPasswordField;

    private Member member;
    /**
     * Initializes the controller class.
     */
    
    public void initMember(Member m) {
        member = m;
        if (member.getImage() != null)profilePicture.setImage(member.getImage());
        nameLabel.setText(member.getName() + " " + member.getSurname());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void reservarPistaOnAction(ActionEvent event) {
    }

    @FXML
    private void misReservasOnAction(ActionEvent event) {
    }

    @FXML
    private void modificarPerfilOnAction(ActionEvent event) {
    }

    @FXML
    private void cancelarButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void aceptarButtonOnAction(ActionEvent event) {
    }
    
}
