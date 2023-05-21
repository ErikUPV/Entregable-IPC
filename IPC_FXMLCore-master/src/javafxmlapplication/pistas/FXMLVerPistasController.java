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
    
    
    
    
    protected static int numPista;
    
    public static int getNumPista() {
        return numPista;
    }
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
        
        try {
            club = Club.getInstance();
            
            
        
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLVerPistasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void pista1ButtonOnAction(ActionEvent event) throws IOException {
        numPista = 1;
        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(1);
            
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista2ButtonOnAction(ActionEvent event) throws IOException {
            numPista = 2;
            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(2);
            
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista3ButtonOnAction(ActionEvent event) throws IOException {
            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(3);
            numPista = 3;
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista4ButtonOnAction(ActionEvent event) throws IOException {
            numPista = 4;
            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(4);
            
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista5ButtonOnAction(ActionEvent event) throws IOException {
            numPista = 5;
            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(5);
            
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista6ButtonOnAction(ActionEvent event) throws IOException {
            numPista = 6;
            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(6);
            
            JavaFXMLApplication.setRoot(root);
    }
    
}
