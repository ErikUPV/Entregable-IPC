/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.inicio;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import javafxmlapplication.autenticacion.FXMLAutenticacionController;
import model.*;

/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private StackPane inicioStackpane;
    @FXML
    private VBox inicioVBOX;
    @FXML
    private HBox inicioHBOX;

    private Club club;
    @FXML
    private Button ini;
    @FXML
    private Button reg;
    @FXML
    private Button accederP;

    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            club = Club.getInstance();
            

        } catch (ClubDAOException | IOException e) {

        }
        
        List<Booking> l = club.getForDayBookings(LocalDate.now());
        
        
        for(Booking b : l) {
            System.out.println("Day booking: " + b.getMadeForDay() + " " + b.getFromTime() + " Court: " + b.getCourt().getName());
        }

//        BackgroundImage myBI= new BackgroundImage(new Image("/imagenes/pexels-pixabay-209977.jpg",32,32,false,true),
//        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//        BackgroundSize.DEFAULT);
////then you set to your node
//        loginStackpane.setBackground(new Background(myBI));
        inicioVBOX.maxWidthProperty().bind(inicioHBOX.prefWidthProperty().multiply(0.5));

    }

    @FXML
    private void IniciarSesionOnAction(ActionEvent event) throws IOException {
//        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.AUTENTICACION);
//        Parent root = miCargador.getRoot();
//        if (root == null) {
//            root = miCargador.load();
//        }
        JavaFXMLApplication.setRoot(Paginas.AUTENTICACION);
    }

    @FXML
    private void registrarseOnAction(ActionEvent event) throws IOException {
//        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.REGISTRO);
//        Parent root = miCargador.getRoot();
//        if (root == null) {
//            root = miCargador.load();
//        }
        JavaFXMLApplication.setRoot(Paginas.REGISTRO);
    }

    @FXML
    private void accederInvOnAction(ActionEvent event) throws IOException {
//        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTAS);
//        Parent root = miCargador.getRoot();
//        if (root == null) {
//            root = miCargador.load();
//        }
        FXMLAutenticacionController.memberProperty().setValue(null);
        JavaFXMLApplication.setRoot(Paginas.PISTAS);
    }

}
