/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.pistas;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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

    private Label[] pistas = {pista1, pista2, pista3, pista4, pista5, pista6};
    private Club club;
    @FXML
    private BorderPane borderPane;
    @FXML
    private GridPane mainGridPane;
    @FXML
    private VBox mainVBox;
    @FXML

    private ImageView iv1;
    @FXML
    private ImageView iv2;
    @FXML
    private ImageView iv3;
    @FXML
    private ImageView iv4;
    @FXML
    private ImageView iv5;
    @FXML
    private ImageView iv6;

    @FXML
    private Button volverPista;
    @FXML
    private Button pista1B;
    @FXML
    private Button pista2B;
    @FXML
    private Button pista3B;
    @FXML
    private Button pista4B;
    @FXML
    private Button pista5B;
    @FXML
    private Button pista6B;
      @FXML
    private ComboBox<?> comboBox;
    @FXML
    private Label disp1;
    @FXML
    private Label disp2;
    @FXML
    private Label disp3;
    @FXML
    private Label disp4;
    @FXML
    private Label disp5;
    @FXML
    private Label disp6;
    @FXML
    private Button buscarUserButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        FXMLAutenticacionController.memberProperty().addListener((ob, oldv, newv) -> {
            member = (Member) newv;
        });
        mainVBox.maxHeightProperty().bind(borderPane.heightProperty().multiply(0.8));
        mainVBox.maxWidthProperty().bind(borderPane.widthProperty().multiply(0.9));

        mainGridPane.minHeightProperty().bind(mainVBox.heightProperty().multiply(0.8));

        numPista = new SimpleIntegerProperty();
        try {
            club = Club.getInstance();

        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLVerPistasController.class.getName()).log(Level.SEVERE, null, ex);
        }

        iv1.fitWidthProperty().bind(mainGridPane.heightProperty().multiply(0.3));
        iv2.fitWidthProperty().bind(mainGridPane.heightProperty().multiply(0.3));
        iv3.fitWidthProperty().bind(mainGridPane.heightProperty().multiply(0.3));
        iv4.fitWidthProperty().bind(mainGridPane.heightProperty().multiply(0.3));
        iv5.fitWidthProperty().bind(mainGridPane.heightProperty().multiply(0.3));
        iv6.fitWidthProperty().bind(mainGridPane.heightProperty().multiply(0.3));

        iv1.fitHeightProperty().bind(mainGridPane.heightProperty().multiply(0.3));
        iv2.fitHeightProperty().bind(mainGridPane.heightProperty().multiply(0.3));
        iv3.fitHeightProperty().bind(mainGridPane.heightProperty().multiply(0.3));
        iv4.fitHeightProperty().bind(mainGridPane.heightProperty().multiply(0.3));
        iv5.fitHeightProperty().bind(mainGridPane.heightProperty().multiply(0.3));
        iv6.fitHeightProperty().bind(mainGridPane.heightProperty().multiply(0.3));

       
        
        
        

    }

    @FXML
    private void pista1ButtonOnAction(ActionEvent event) throws IOException {
//        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
//            Parent root = miCargador.getRoot();
//            if (root == null) root = miCargador.load();
        numPista.setValue(1);
//            FXMLPistaConcretaController controlador = miCargador.getController();
//            controlador.initPista(1);

        JavaFXMLApplication.setRoot(Paginas.PISTA_CONCRETA);
    }

    @FXML
    private void pista2ButtonOnAction(ActionEvent event) throws IOException {

//            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
//            Parent root = miCargador.getRoot();
//            if (root == null) root = miCargador.load();
        numPista.setValue(2);

//            FXMLPistaConcretaController controlador = miCargador.getController();
//            controlador.initPista(2);
        JavaFXMLApplication.setRoot(Paginas.PISTA_CONCRETA);
    }

    @FXML
    private void pista3ButtonOnAction(ActionEvent event) throws IOException {

//            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
//            Parent root = miCargador.getRoot();
//            if (root == null) root = miCargador.load();
        numPista.setValue(3);
//            FXMLPistaConcretaController controlador = miCargador.getController();
//            controlador.initPista(3);
        JavaFXMLApplication.setRoot(Paginas.PISTA_CONCRETA);
    }

    @FXML
    private void pista4ButtonOnAction(ActionEvent event) throws IOException {

//            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
//            Parent root = miCargador.getRoot();
//            if (root == null) root = miCargador.load();
        numPista.setValue(4);
//            FXMLPistaConcretaController controlador = miCargador.getController();
//            controlador.initPista(4);

        JavaFXMLApplication.setRoot(Paginas.PISTA_CONCRETA);
    }

    @FXML
    private void pista5ButtonOnAction(ActionEvent event) throws IOException {

//            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
//            Parent root = miCargador.getRoot();
//            if (root == null) root = miCargador.load();
        numPista.setValue(5);
//            FXMLPistaConcretaController controlador = miCargador.getController();
//            controlador.initPista(5);
        JavaFXMLApplication.setRoot(Paginas.PISTA_CONCRETA);
    }

    @FXML
    private void pista6ButtonOnAction(ActionEvent event) throws IOException {

//            FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTA_CONCRETA);
//            Parent root = miCargador.getRoot();
//            if (root == null) root = miCargador.load();
        numPista.setValue(6);
//            FXMLPistaConcretaController controlador = miCargador.getController();
//            controlador.initPista(6);

        JavaFXMLApplication.setRoot(Paginas.PISTA_CONCRETA);
    }

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {

//        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.INICIO);
//        Parent root = miCargador.getRoot();
//        if (root == null) {
//            root = miCargador.load();
//        }
        JavaFXMLApplication.setRoot(Paginas.ESPACIO_PERSONAL);
    }

    @FXML
    private void buscarUserButtonOnAction(ActionEvent event) {
    }

}
