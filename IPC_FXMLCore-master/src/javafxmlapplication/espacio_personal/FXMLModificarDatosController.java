/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.espacio_personal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import javafxmlapplication.autenticacion.FXMLAutenticacionController;
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
    private Club club;
    private Member member;
    @FXML
    private Label nicknameLabel;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField contrField;
    @FXML
    private TextField repContrField;
    @FXML
    private TextField apellidosTextField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField numBancoField;
    @FXML
    private TextField cvcField;
    private static ObjectProperty memberProperty;
    @FXML
    private Label badInputLabel;
    private boolean contraseñasIguales;
    /**
     * Initializes the controller class.
     */
    
    public void initMember(Member m) {
        System.out.println(member.toString());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contraseñasIguales = true;
        memberProperty = FXMLAutenticacionController.memberProperty();
        memberProperty.addListener((ob, oldv, newv) -> {
            member = (Member) newv;
            profilePicture.setImage(member.getImage());
            nameLabel.setText(member.getName() + " " + member.getSurname());
            nicknameLabel.setText(member.getNickName());
            nombreTextField.setText(member.getName());
            apellidosTextField.setText(member.getSurname());
            telefonoField.setText(member.getTelephone());
            if(member.getCreditCard() != "") {
                numBancoField.setText(member.getCreditCard());
                cvcField.setText("" + member.getSvc());
            }
        });
        repContrField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // When focus is lost from the second field
                String password1 = contrField.getText();
                String password2 = repContrField.getText();
                if (!password1.equals(password2)) {
                    showErrorMessage(contrField, "Passwords do not match!");
                    showErrorMessage(repContrField, "Passwords do not match!");
                    badInputLabel.setText("Passwords don't match");
                    repContrField.setText("");
                    badInputLabel.visibleProperty().setValue(true);
                    contraseñasIguales = false;
                } else {
                    hideErrorMessage(contrField );
                    hideErrorMessage(repContrField );
                    badInputLabel.visibleProperty().setValue(false);
                    contraseñasIguales = true;
                    
                }
            }
        });
        cvcField.setTextFormatter(getTextFormatter(3));
        numBancoField.setTextFormatter(getTextFormatter(16));
        telefonoField.setTextFormatter(getTextFormatter(11));
        
    }    

    @FXML
    private void backButtonOnAction(ActionEvent event) {
        JavaFXMLApplication.setRoot(Paginas.ESPACIO_PERSONAL);
    }

    @FXML
    private void reservarPistaOnAction(ActionEvent event) {
        System.out.println("Espacio personal: " + member.toString());
        JavaFXMLApplication.setRoot(Paginas.PISTAS);
    }

    @FXML
    private void misReservasOnAction(ActionEvent event) {
        JavaFXMLApplication.setRoot(Paginas.ESPACIO_PERSONAL);
    }

    @FXML
    private void modificarPerfilOnAction(ActionEvent event) {
       
       if (nombreTextField.textProperty().getValueSafe().isEmpty() || nombreTextField.textProperty().getValueSafe().isBlank()) showErrorMessage( nombreTextField, "Nombre vacío");
       else hideErrorMessage(nombreTextField);
       
       if (apellidosTextField.textProperty().getValueSafe().isEmpty()) showErrorMessage( apellidosTextField, "Apellido vacío");
       else hideErrorMessage(apellidosTextField);
       
       if (telefonoField.textProperty().getValueSafe().isEmpty()) showErrorMessage( telefonoField, "Teléfono vacío");
       else hideErrorMessage(telefonoField);
       
       if (contrField.textProperty().getValueSafe().isEmpty()) showErrorMessage( contrField, "Contraseña vacía");
       else if (!repContrField.getText().isEmpty())hideErrorMessage(contrField);
       
       if (repContrField.textProperty().getValueSafe().isEmpty()) showErrorMessage( repContrField, "Pw mismatch");
       else hideErrorMessage(repContrField);
       
       if ( (numBancoField.textProperty().getValueSafe().isEmpty() && !  cvcField.textProperty().getValueSafe().isEmpty()) ||(!numBancoField.textProperty().getValueSafe().isEmpty() &&   cvcField.textProperty().getValueSafe().isEmpty()) ){
           showErrorMessage( numBancoField,"Número vacío o CVC vacío" );
           showErrorMessage( cvcField, "");

       } else{
           hideErrorMessage(numBancoField);
           hideErrorMessage(cvcField);
       }
       if(cvcField.getText().isEmpty() && numBancoField.getText().isEmpty()) cvcField.setText("0");
       boolean b = nombreTextField.getText().isEmpty()
               || apellidosTextField.getText().isEmpty()
               || telefonoField.getText().isEmpty()
               || contrField.getText().isEmpty()
               || !contraseñasIguales;
      if(!b){ 
            member.setName(nombreTextField.getText());
            member.setSurname(apellidosTextField.getText());
            member.setTelephone(telefonoField.getText());
            member.setPassword(contrField.getText());
            if ((numBancoField.getText().isEmpty() && !cvcField.getText().equals("0"))) {
                member.setCreditCard(numBancoField.getText());
                member.setSvc(Integer.parseInt(cvcField.getText()));
            }
      }
        /* Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        event2 -> { //FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.REGISTRO2);
//                                    Parent root = miCargador.getRoot();
//                                    if (root == null) try {
//                                        root = miCargador.load();


                        
                                    JavaFXMLApplication.setRoot(Paginas.REGISTRO2);}),
                new KeyFrame(Duration.seconds(1.5),
                        event2 -> {
                                    JavaFXMLApplication.setRoot(Paginas.INICIO);
                                    
                                 ;})
        );
        timeline.setCycleCount(1);
        timeline.play();*/
        JavaFXMLApplication.setRoot(Paginas.ESPACIO_PERSONAL);
    }
    
    private void showErrorMessage ( TextField field, String msg) {
        String source = field.getId();
        badInputLabel.visibleProperty().setValue(true);
        field.styleProperty().setValue("-fx-background-color: #FCE5E0;"
                + "-fx-border-color: red;"
                + "-fx-border-radius: 5 5 5 5;"
                + "-fx-text-fill: red;"
                + "-fx-prompt-text-fill: red");
       
                //field.setPromptText(msg);
    }
    
    private void hideErrorMessage(TextField field){
        
        field.styleProperty().setValue(""); 
    }
    
    private  TextFormatter<String> getTextFormatter(int e) {
        TextFormatter<String> formatter2 = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,"+ e + "}")) {
                return change;
            }
            return null;
        });
        return formatter2;
    }
}
