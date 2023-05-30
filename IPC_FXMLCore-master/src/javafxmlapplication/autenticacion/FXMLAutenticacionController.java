/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.autenticacion;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import javafxmlapplication.espacio_personal.FXMLEspacioPController;
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

    private static Member member;
    @FXML
    private ColumnConstraints columnaPrincipal;

    private static ObjectProperty memberProperty;
    @FXML
    private Label debugLabel;

    private FXMLEspacioPController c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        memberProperty = new SimpleObjectProperty(member);
        try {
            club = Club.getInstance();
            File f = new File("src/javafxmlapplication/imagenes/home.png");
            Image i = new Image(f.toURI().toString());
            club.registerMember("ferndando", "alonso diaz", "999999999", "admin", "admin", "1234567890123456", 333, i);
            userTextField.setText("admin");
            passwordField.setText("admin");
        } catch (ClubDAOException | IOException e) {
            System.err.println("Error hallado: " + e);
        }

        //debugLabel.setText("" + club.getCourts().toString());
        // TODO
        columnaPrincipal.minWidthProperty().set(400);
        // TODO
        columnaPrincipal.maxWidthProperty().bind(borderPane.widthProperty().multiply(0.3));

    }

    @FXML
    private void backButtonOnAction(ActionEvent event) {
        JavaFXMLApplication.setRoot(Paginas.INICIO);
        JavaFXMLApplication.borrarTextField(passwordField, userTextField);
    }

    @FXML
    private void loginButtonOnAction(ActionEvent event) throws InterruptedException, IOException {
        if (userTextField.getText().isEmpty() || passwordField.getText().isEmpty()) return;
        if (!club.existsLogin(userTextField.getText())) {
            Alert a = new Alert(AlertType.ERROR); // INFORMATION
            a.setTitle("Error");
            a.setHeaderText("Error al introducir los datos");
            a.setContentText("El usuario no existe");
           startAlert(a);
            a.showAndWait();
            return;
        }

        if (club.getMemberByCredentials(userTextField.getText(), passwordField.getText()) != null) {
            member = club.getMemberByCredentials(userTextField.getText(), passwordField.getText());
        } 

        if (member != null) {
            System.out.println("login exitoso");
            //debugLabel.textProperty().set("Bienvenido " + member.getNickName());

//                FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.ESPACIO_PERSONAL);
//                FXMLEspacioPersonalController controlador = miCargador.getController();
//
//                Parent root = miCargador.getRoot();
//                if (root == null) {
//                    root = miCargador.load();
//                }
            memberProperty.setValue(member);

            System.out.println(member.getName() + " " + member.getSurname());
            c = FXMLEspacioPController.getController();

            c.setDefault();
            JavaFXMLApplication.setRoot(Paginas.ESPACIO_P);
            JavaFXMLApplication.borrarTextField(passwordField, userTextField);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0),
                            event2 -> { //FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.REGISTRO2);
//                                    Parent root = miCargador.getRoot();
//                                    if (root == null) try {
//                                        root = miCargador.load();

                                JavaFXMLApplication.setRoot(Paginas.REGISTRO2);
                            }),
                    new KeyFrame(Duration.seconds(1.5),
                            event2 -> {
                                JavaFXMLApplication.setRoot(Paginas.ESPACIO_P);
                                ;
                            })
            );
            timeline.setCycleCount(1);
            timeline.play();

        } else {
            Alert a = new Alert(AlertType.ERROR); // INFORMATION
            a.setTitle("Error");
            a.setHeaderText("Error al introducir los datos");
            a.setContentText("Contraseña incorrecta");
            startAlert(a);
            a.showAndWait();
        }

    }

    @FXML
    private void registrarButtonOnAction(ActionEvent event) throws IOException {
//        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.REGISTRO);
//        Parent root = miCargador.getRoot();
//        if (root == null) {
//            root = miCargador.load();
//        }
        JavaFXMLApplication.borrarTextField(passwordField, userTextField);
        JavaFXMLApplication.setRoot(Paginas.REGISTRO);
    }

    public static Member getMember() {
        return member;
    }

    public static ObjectProperty memberProperty() {
        return memberProperty;
    }

    public static void cerrarSesion() {
        memberProperty.setValue(null);
        JavaFXMLApplication.setRoot(Paginas.INICIO);
    }
    private void startAlert(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../estilos.css").toExternalForm());
        dialogPane.getStyleClass().add("myAlert");
    }

}
