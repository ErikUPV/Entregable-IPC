/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.pistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    
    
    
    
    public void initMember(Member m) {
        member = m;
        
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        member = FXMLAutenticacionController.getMember();
        System.out.println("ver pistas: " + member.getNickName());
        System.out.println(member.toString());
    }    

    @FXML
    private void pista1ButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(1);
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista2ButtonOnAction(ActionEvent event) throws IOException {
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
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista4ButtonOnAction(ActionEvent event) throws IOException {
            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(4);
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista5ButtonOnAction(ActionEvent event) throws IOException {
            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(5);
            JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void pista6ButtonOnAction(ActionEvent event) throws IOException {
            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
            Parent root = miCargador.getRoot();
            if (root == null) root = miCargador.load();
            FXMLPistaConcretaController controlador = miCargador.getController();
            controlador.initPista(6);
            JavaFXMLApplication.setRoot(root);
    }
    
}
