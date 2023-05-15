/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.espacio_personal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Héctor
 */
public class FXMLEspacioPersonalController implements Initializable {

    @FXML
    private Button backButton;
    Member member;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Label name;
    @FXML
    private Label nickName;
    @FXML
    private Button modificarButton;
    
    public void initMember(Member m) {
        member = m;
        profilePicture.setImage(member.getImage());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //profilePicture.setImage(member.getImage());
    }    

    @FXML
    private void backButtonOnAction(ActionEvent event) {
        JavaFXMLApplication.setRoot(Paginas.INICIO);
    }

    @FXML
    private void modificarButtonOnAction(ActionEvent event) {
    }
    
}
