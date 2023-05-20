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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Héctor
 */
public class FXMLEspacioPersonalController implements Initializable {

    Member member;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Label nameLabel;
    
    public void initMember(Member m) {
        member = m;
        profilePicture.setImage(member.getImage());
         nameLabel.setText(member.getName() + " " + member.getSurname());
       
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //profilePicture.setImage(member.getImage());
    }
    private void backButtonOnAction(ActionEvent event) {
         FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.INICIO);
        JavaFXMLApplication.setRoot(miCargador.getRoot());
    }

    private void modificarButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/javafxmlapplication/espacio_personal/FXMLDocument.fxml"));
        Parent root = cargador.load();
        FXMLDocumentController controladorModif = cargador.getController();
        controladorModif.initMember(member);
        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ventana de modificación");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
}
