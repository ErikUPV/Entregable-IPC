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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Héctor
 */
public class FXMLModificarDController implements Initializable {

    @FXML
    private VBox cambioVBOX;
    @FXML
    private TextField nombreTextField;
    @FXML
    private PasswordField contrField;
    @FXML
    private PasswordField repContrField;
    @FXML
    private TextField apellidosTextField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField numBancoField;
    @FXML
    private TextField cvcField;
    @FXML
    private Label badInputLabel;
    @FXML
    private Button modificar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void elegirImagenOnAction(ActionEvent event) {
    }

    @FXML
    private void modificarOnAction(ActionEvent event) {
    }
    
}
