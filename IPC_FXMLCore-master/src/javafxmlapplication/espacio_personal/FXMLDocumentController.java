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
import javafx.scene.control.TextField;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Héctor
 */
public class FXMLDocumentController implements Initializable {

    Member member;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initMember(Member m) {
        member = m;
    }

    @FXML
    private void cancelarButtonOnAction(ActionEvent event) {
        nameField.getScene().getWindow().hide();
    }

    @FXML
    private void aceptarButtonOnAction(ActionEvent event) {
        nameField.getScene().getWindow().hide();
    }
    
}
