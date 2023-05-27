/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.espacio_personal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafxmlapplication.autenticacion.FXMLAutenticacionController;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Héctor
 */
public class FXMLEspacioPController implements Initializable {

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
    private Button cerrar;
    @FXML
    private Label nameLabel;
    @FXML
    private Label nicknameLabel;
    private Club club;
    private Member member;
    private static ObjectProperty memberProperty;
    @FXML
    private Pane paneEscena;

    /**
     * Initializes the controller class.
     */
    public void initMember(Member m) throws ClubDAOException, IOException {
        System.out.println(member.toString());
        club = Club.getInstance();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        memberProperty = FXMLAutenticacionController.memberProperty();
        memberProperty.addListener((ob, oldv, newv) -> {
            member = (Member) newv;
            profilePicture.setImage(member.getImage());
            nameLabel.setText(member.getName() + " " + member.getSurname());
            nicknameLabel.setText(member.getNickName());
        });
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
    private void modificarPerfilOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("espacio_personal/FXMLModificarD.fxml"));
        
    }

    @FXML
    private void cerrarSesionOnAction(ActionEvent event) {
    }
    
}
