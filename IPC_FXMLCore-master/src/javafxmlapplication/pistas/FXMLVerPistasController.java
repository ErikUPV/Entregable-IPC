/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.pistas;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import javafxmlapplication.autenticacion.FXMLAutenticacionController;
import javafxmlapplication.espacio_personal.FXMLEspacioPersonalController;
import model.*;
/**
 * FXML Controller class
 *
 * @author Héctor
 */


public class FXMLVerPistasController implements Initializable {

    
    protected Member member;
    
    
    
    
    protected static IntegerProperty numPista;
    
    
    @FXML
    private Label pista1;
    @FXML
    private Label pista2;
    @FXML
    private Label pista3;
    @FXML
    private Label pista4;
    @FXML
    private Label pista5;
    @FXML
    private Label pista6;
    
    private Label[] pistas = {pista1,pista2,pista3,pista4,pista5,pista6};
    private Club club;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        member = FXMLAutenticacionController.getMember();
        System.out.println("ver pistas: " + member.getNickName());
        System.out.println(member.toString());
        
        numPista = new SimpleIntegerProperty();
        try {
            club = Club.getInstance();
            
            
        
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLVerPistasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void pista1ButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            numPista.setValue(1);
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(1);
            
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista2ButtonOnAction(ActionEvent event) throws IOException {
        
            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            numPista.setValue(2);

            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(2);
            
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista3ButtonOnAction(ActionEvent event) throws IOException {
                

            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            numPista.setValue(3);
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(3);
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista4ButtonOnAction(ActionEvent event) throws IOException {
                

            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            numPista.setValue(4);
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(4);
            
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista5ButtonOnAction(ActionEvent event) throws IOException {
                

            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            numPista.setValue(5);
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(5);
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista6ButtonOnAction(ActionEvent event) throws IOException {
            

            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            numPista.setValue(6);
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(6);
            
            JavaFXMLApplication.setRoot(root);
    }
    
}
