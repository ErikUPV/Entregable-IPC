/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.espacio_personal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import model.Member;

/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    //========================================================
    // objects defined into FXML file with fx:id 
    @FXML
    private Button buttonClick;
    @FXML
    private Label labelMessage;
    private Member member;
    
    public void initMember(Member m) {
        member = m;
    }
    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmacion");
        alert.setHeaderText("TODO BIEN, MIEMBRO PASADO CORRECTAMENTE");
        alert.setContentText("usuario: " + member.getNickName() + ", contraseña: " + member.getPassword());
        alert.showAndWait();
        JavaFXMLApplication.setRoot(Paginas.AUTENTICACION);
    }
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
