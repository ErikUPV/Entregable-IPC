/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.espacio_personal;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import javafxmlapplication.autenticacion.FXMLAutenticacionController;
import static javafxmlapplication.autenticacion.FXMLAutenticacionController.cerrarSesion;
import javafxmlapplication.pistas.FXMLVerPistasController;
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
    private Club club;
    private Member member;
    @FXML
    private Label nicknameLabel;
    @FXML
    private Button cerrar;
    /**
     * Initializes the controller class.
     */
    
    public void initMember(Member m) {
        System.out.println(member.toString());
        if (member.getImage() != null) {
            profilePicture.setImage(member.getImage());
        }
        nameLabel.setText(member.getName() + " " + member.getSurname());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        System.out.println("hy");
//        member = FXMLAutenticacionController.getMember();
//        System.out.println(member.getNickName() + " heyyy");
        try {
            club = Club.getInstance();
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLEspacioPersonalController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        nameLabel.setText(member.getName() + " " + member.getSurname()); 
//        nicknameLabel.setText(member.getNickName());
       
    }    

    @FXML
    private void backButtonOnAction(ActionEvent event) {
        JavaFXMLApplication.setRoot(Paginas.ESPACIO_PERSONAL);
//        Parent root = miCargador.getRoot();
//        if (root == null) {
//            try {
//                root = miCargador.load();
//            } catch (IOException ex) {
//                Logger.getLogger(FXMLModificarDatosController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void reservarPistaOnAction(ActionEvent event) {
        System.out.println("Espacio personal: " + member.toString());
        JavaFXMLApplication.setRoot(Paginas.PISTAS);
//        Parent root = miCargador.getRoot();
//        FXMLVerPistasController controlador = miCargador.getController();
//
//        if (root == null) {
//            try {
//                root = miCargador.load();
//            } catch (IOException ex) {
//                Logger.getLogger(FXMLModificarDatosController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        System.out.println("a");
//
//        JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void misReservasOnAction(ActionEvent event) {
        JavaFXMLApplication.setRoot(Paginas.ESPACIO_PERSONAL);
//        Parent root = miCargador.getRoot();
//        if (root == null) {
//            try {
//                root = miCargador.load();
//            } catch (IOException ex) {
//                Logger.getLogger(FXMLModificarDatosController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        FXMLModificarDatosController controlador = miCargador.getController();
//        controlador.initMember(member);
//
//        JavaFXMLApplication.setRoot(root);
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

    @FXML
    private void cerrarMOnAction(ActionEvent event) {
        cerrarSesion();
    }
    
}
