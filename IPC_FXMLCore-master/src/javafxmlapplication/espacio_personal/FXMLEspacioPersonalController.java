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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import javafxmlapplication.autenticacion.FXMLAutenticacionController;
import javafxmlapplication.pistas.FXMLVerPistasController;
import model.*;

/**
 * FXML Controller class
 *
 * @author Héctor
 */
public class FXMLEspacioPersonalController implements Initializable {

    private static Member member;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Label nameLabel;
    @FXML
    private Button backButton;
    @FXML
    private Button reservarPista;
    @FXML
    private Button misReservas;
    @FXML
    private Button modificarPerfil;

    private Club club;
    @FXML
    private AnchorPane cambioAnchorPane;
    @FXML
    private TableView<?> reservasT;

    public void initMember(Member m) {

        System.out.println(member.toString());
        if (member.getImage() != null) {
            profilePicture.setImage(member.getImage());
        }
        nameLabel.setText(member.getName() + " " + member.getSurname());

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hy");
        member = FXMLAutenticacionController.getMember();
        System.out.println(member.getNickName() + " heyyy");
        try {
            club = Club.getInstance();
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLEspacioPersonalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Establecer la propiedad de ancho y alto del TableView para que se redimensione con el AnchorPane
        //reservasT.prefWidthProperty().bind(cambioAnchorPane.widthProperty());
        //reservasT.prefHeightProperty().bind(cambioAnchorPane.heightProperty());
    }

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {

        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.INICIO);
        Parent root = miCargador.getRoot();
        if (root == null) {
            root = miCargador.load();
        }
        JavaFXMLApplication.setRoot(root);
    }

    private void modificarButtonOnAction(ActionEvent event) throws IOException {
        cambioAnchorPane.getChildren().clear();
    }

    @FXML
    private void reservarPistaOnAction(ActionEvent event) throws IOException {
        System.out.println("Espacio personal: " + member.toString());
        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTAS);
        Parent root = miCargador.getRoot();
        FXMLVerPistasController controlador = miCargador.getController();

        if (root == null) {
            root = miCargador.load();
        }
        System.out.println("a");

        JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void misReservasOnAction(ActionEvent event) {
    }

    @FXML
    private void modificarPerfilOnAction(ActionEvent event) throws IOException {
        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.MODIFICAR_DATOS);
        Parent root = miCargador.getRoot();
        if (root == null) {
            root = miCargador.load();
        }
        FXMLModificarDatosController controlador = miCargador.getController();
        controlador.initMember(member);

        JavaFXMLApplication.setRoot(root);
    }

}

