/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.autenticacion;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.VBox;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import javafxmlapplication.espacio_personal.FXMLEspacioPersonalController;
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
            club.registerMember("nombre", "apellido", "999999999", "admin", "admin", "51555555555", 333, i);
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
//        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.INICIO);
       JavaFXMLApplication.setRoot(Paginas.INICIO);


    }

    @FXML
    private void loginButtonOnAction(ActionEvent event) throws InterruptedException, IOException {
        try {
             member = club.getMemberByCredentials(userTextField.getText(), passwordField.getText());

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
                JavaFXMLApplication.setRoot(Paginas.ESPACIO_PERSONAL);

            }
        } catch (NullPointerException e) {
            //debugLabel.setText("Por favor introduzca unas credenciales válidas");
        }
    }

    @FXML
    private void registrarButtonOnAction(ActionEvent event) throws IOException {
//        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.REGISTRO);
//        Parent root = miCargador.getRoot();
//        if (root == null) {
//            root = miCargador.load();
//        }

        JavaFXMLApplication.setRoot(Paginas.REGISTRO);
    }

    public static Member getMember() {
        return member;
    }

    public static ObjectProperty memberProperty() {
        return memberProperty;
    }
    
    public static void cerrarSesion() {
        member = null;
        JavaFXMLApplication.setRoot(Paginas.INICIO);
    }

}
