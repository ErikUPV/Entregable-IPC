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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import javafxmlapplication.autenticacion.FXMLAutenticacionController;
import static javafxmlapplication.autenticacion.FXMLAutenticacionController.cerrarSesion;
import javafxmlapplication.pistas.FXMLPistaConcretaController;
import javafxmlapplication.pistas.FXMLVerPistasController;
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
    private VBox paneEscena;
    FXMLModificarDatosController controlador;
    @FXML
    private ImageView profilePictureImg;
    @FXML
    private HBox mainVBox;
    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    public void initMember(Member m) throws ClubDAOException, IOException {
        System.out.println(member.toString());
        club = Club.getInstance();
    }

    public void updateItems() {
        
        nameLabel.setText(member.getName() + " " + member.getSurname());
        nicknameLabel.setText(member.getNickName());
        profilePictureImg.setImage(member.getImage());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        memberProperty = FXMLAutenticacionController.memberProperty();
        memberProperty.addListener((ob, oldv, newv) -> {
            member = (Member) newv;
            profilePictureImg.setImage(member.getImage());
            nameLabel.setText(member.getName() + " " + member.getSurname());
            nicknameLabel.setText(member.getNickName());
        });
        
        mainVBox.maxWidthProperty().bind(borderPane.widthProperty().multiply(0.9));
        mainVBox.maxHeightProperty().bind(borderPane.heightProperty().multiply(0.8));
        
        

    }

    @FXML
    private void backButtonOnAction(ActionEvent event) {
        JavaFXMLApplication.setRoot(Paginas.INICIO);
    }

    @FXML
    private void reservarPistaOnAction(ActionEvent event) {
        if (controlador != null) {
            if (!controlador.confirmaCambiados()) {
                return;
            }
        }
        FXMLVerPistasController controlador = FXMLVerPistasController.getController();
        controlador.initializeComboBox();
        JavaFXMLApplication.setRoot(Paginas.PISTAS);
    }

    @FXML
    private void misReservasOnAction(ActionEvent event) throws ClubDAOException {
        if (controlador != null) {
            if (!controlador.confirmaCambiados()) {
                return;
            }
        }
        paneEscena.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxmlapplication/espacio_personal/FXMLMisReservas.fxml"));
            Pane modificarD = loader.load();
            FXMLMisReservasController controlador = loader.getController();
            
            controlador.initMember(member, this);

            paneEscena.getChildren().add(modificarD);
            controlador.setPane(paneEscena);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxmlapplication/espacio_personal/FXMLModificarDatos.fxml"));
            Pane modificarD = loader.load();
            controlador = loader.getController();
            controlador.initMember(member, this);

            paneEscena.getChildren().add(modificarD);
            
        } catch (IOException e) {
            java.util.logging.Logger.getLogger(FXMLEspacioPController.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        modificarPerfil.disableProperty().setValue(true);
        misReservas.disableProperty().setValue(false);
    }

    @FXML
    private void cerrarSesionOnAction(ActionEvent event) {
        cerrarSesion();
    }
    
    public Pane getPane() {
        return paneEscena;
    }
}
