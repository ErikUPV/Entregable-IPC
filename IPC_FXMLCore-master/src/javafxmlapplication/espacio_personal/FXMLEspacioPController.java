/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.espacio_personal;

import com.sun.javafx.logging.PlatformLogger.Level;
import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import javafxmlapplication.autenticacion.FXMLAutenticacionController;
import static javafxmlapplication.autenticacion.FXMLAutenticacionController.cerrarSesion;
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
        JavaFXMLApplication.setRoot(Paginas.PISTAS);
    }

    @FXML
    private void misReservasOnAction(ActionEvent event) throws ClubDAOException {
        paneEscena.getChildren().clear();
        try {
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("/javafxmlapplication/espacio_personal/FXMLMisReservas.fxml"));
            Pane modificarD = loader.load();
            FXMLMisReservasController controlador = loader.getController();
            controlador.initMember(member);
            
            paneEscena.getChildren().add(modificarD);
        } catch (IOException e) {
            System.out.println(e);
        }
        misReservas.disableProperty().setValue(true);
        modificarPerfil.disableProperty().setValue(false);
    }

    @FXML
    private void modificarPerfilOnAction(ActionEvent event) throws IOException, ClubDAOException {
        paneEscena.getChildren().clear();
        try {
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("/javafxmlapplication/espacio_personal/FXMLModificarDatos.fxml"));
            Pane modificarD = loader.load();
            FXMLModificarDatosController controlador = loader.getController();
            controlador.initMember(member);
            
            paneEscena.getChildren().add(modificarD);
        } catch (IOException e) {
            System.out.println(e);
        }
        modificarPerfil.disableProperty().setValue(true);
        misReservas.disableProperty().setValue(false);
}

    @FXML
    private void cerrarSesionOnAction(ActionEvent event) {
        cerrarSesion();
    }
}
