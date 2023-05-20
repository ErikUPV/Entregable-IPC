/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.pistas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import javafxmlapplication.autenticacion.FXMLAutenticacionController;
import model.*;

/**
 * FXML Controller class
 *
 * @author Héctor
 */
public class FXMLPistaConcretaController implements Initializable {

    @FXML
    private ImageView imagenPista;
    @FXML
    private Label title;
    @FXML
    private VBox mainVBox;
    @FXML
    private BorderPane borderPane;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<?> pistaTableView;
    
    private Member member = null;
    
    private Club club;
    @FXML
    private ComboBox<?> comboBox;
    /**
     * Initializes the controller class.
     */
  
    public void initPista(int pista) {
        title.setText("Reservar Pista " + pista);
        File f;
        switch(pista) {
            case 1:
                f = new File("src/javafxmlapplication/imagenes/courtImage-v1.png");
                break;
            case 2:
                f = new File("src/javafxmlapplication/imagenes/courtImage-v2.png");
                break;
            case 3:
                f = new File("src/javafxmlapplication/imagenes/courtImage-v3.png");
                break;
            case 4:
                f = new File("src/javafxmlapplication/imagenes/courtImage-v4.png");
                break;
            case 5:
                f = new File("src/javafxmlapplication/imagenes/courtImage-v5.png");
                break;
            default:
                f = new File("src/javafxmlapplication/imagenes/courtImage-v6.png");
                break;
        }
        Image i = new Image(f.toURI().toString());
        imagenPista.setImage(i);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        member = FXMLAutenticacionController.getMember();
        System.out.println("pista concreta: " + member.getNickName());
        try {
            // TODO
            club = Club.getInstance();
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLPistaConcretaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        comboBox.setPromptText(member.getName() + " " + member.getSurname());
        
        pistaTableView.minHeightProperty().bind(mainVBox.heightProperty().multiply(0.67));
        
        borderPane.widthProperty().addListener( (observable, oldv, newv) -> {
            if (newv.intValue() > 1200) {
                mainVBox.maxWidthProperty().bind(borderPane.widthProperty().multiply(0.6));
                mainVBox.minWidthProperty().setValue(1200);
            } else {
                mainVBox.maxWidthProperty().unbind();
                mainVBox.minWidthProperty().setValue(800);
            }
        });
        
        
        datePicker.setDayCellFactory((DatePicker picker) -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) < 0);
                }
            };
        });
    }        

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTAS);
        Parent root = miCargador.getRoot();
        if (root == null) root = miCargador.load();
        JavaFXMLApplication.setRoot(root);
    }
    
    

}
