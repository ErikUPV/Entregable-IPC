/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.pistas;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import javafxmlapplication.autenticacion.FXMLAutenticacionController;
import javafxmlapplication.espacio_personal.FXMLEspacioPController;
import model.*;

/**
 * FXML Controller class
 *
 * @author erikb
 */
public class FXMLBusquedaUsuarioController implements Initializable {

    @FXML
    private Button volverPista;
    @FXML
    private ChoiceBox<String> comboBox;
    @FXML
    private Label title;
    @FXML
    private Label dayLabel;
    @FXML
    private VBox tableViewVBox;
    @FXML
    private TableView<Booking> pistaTableView;
    @FXML
    private TableColumn<Booking, String> horaCol;
    @FXML
    private TextField buscarTextField;

    private List<Booking> lista;
    private ObservableList<Booking> listaObservable;

    private Club club;
    @FXML
    private BorderPane borderPane;
    @FXML
    private VBox mainVBox;
    @FXML
    private TableColumn<Booking, String> diaCol;
    @FXML
    private TableColumn<Booking, String> pistaCol;

    private final String titulo = "Mostrando reservas de ";

    private List<String> comboList;
    private ObservableList<String> comboObsList;

    private Member member;

    private static FXMLBusquedaUsuarioController controlador;

    private FXMLEspacioPController c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlador = this;
        c = FXMLEspacioPController.getController();
        comboBox.visibleProperty().setValue(false);

        try {
            club = Club.getInstance();
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLBusquedaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLAutenticacionController.memberProperty().addListener((var ob, var oldv, var newv) -> {
            if (newv != null) {
                member = (Member) newv;
                comboBox.visibleProperty().setValue(true);
            } else {
                comboBox.visibleProperty().setValue(false);

            }

        });
        
        
        lista = new ArrayList<>();
        listaObservable = FXCollections.observableArrayList(lista);
        pistaTableView.setItems(listaObservable);

        mainVBox.maxWidthProperty().bind(borderPane.widthProperty().multiply(0.8));
        mainVBox.maxHeightProperty().bind(borderPane.heightProperty().multiply(0.8));

        pistaCol.prefWidthProperty().bind(pistaTableView.widthProperty().multiply(0.34));
        diaCol.prefWidthProperty().bind(pistaTableView.widthProperty().multiply(0.33));
        horaCol.prefWidthProperty().bind(pistaTableView.widthProperty().multiply(0.33));

        // TODO
        diaCol.setCellValueFactory(cellData -> {
            Booking item = cellData.getValue();
            String day = item.getMadeForDay().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return new SimpleStringProperty(day);
        });

        horaCol.setCellValueFactory(cellData -> {
            Booking item = cellData.getValue();
            String hourF = item.getFromTime().toString();
            return new SimpleStringProperty(hourF);
        });

        pistaCol.setCellValueFactory(cellData -> {
            Booking item = cellData.getValue();
            String pista = item.getCourt().getName().substring(5);
            return new SimpleStringProperty(pista);
        });

    }

    @FXML
    private void backButtonOnAction(ActionEvent event) {
        JavaFXMLApplication.setRoot(Paginas.PISTAS);
    }

    
    @FXML
    private void buscarButtonOnAction(ActionEvent event) {
        String text = buscarTextField.getText();

        if (text.isEmpty() || text.isBlank()) {
            Alert alert = new Alert(AlertType.ERROR);
            startAlert(alert);
            alert.setTitle("Error");
            alert.setHeaderText("Error en la búsqueda");
            alert.setContentText("No puede introducir un carácter vacío");
            alert.showAndWait();
        } else if (!club.existsLogin(text)) {
            Alert alert = new Alert(AlertType.ERROR);
            startAlert(alert);
            alert.setTitle("Error");
            alert.setHeaderText("Error en la búsqueda");
            alert.setContentText("Este usuario no existe");
            alert.showAndWait();
        } else {
            listaObservable.clear();
            List<Booking> l = club.getUserBookings(text);
            for (Booking b : l) {
                if (b.getMadeForDay().compareTo(LocalDate.now()) >= 0) {
                    listaObservable.add(b);
                }
            }
            title.setText(titulo + text);

        } 
    }

    private void startAlert(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../estilos.css").toExternalForm());
        dialogPane.getStyleClass().add("myAlert");
    }

    public void initializeComboBox() {
        if (member != null) {
            comboBox.setValue(member.getName() + " - Opciones");
        comboList = new ArrayList<>();

        comboObsList = FXCollections.observableArrayList(comboList);
        comboObsList.addAll("Espacio Personal", "Cerrar sesión");

        comboBox.setItems(comboObsList);

        comboBox.getSelectionModel().selectedItemProperty().addListener((ob, oldv, newv) -> {
            if (newv == null) {
                return;
            }

            if (newv.equals("Cerrar sesión")) {
                FXMLAutenticacionController.cerrarSesion();

            } else if (newv.equals("Espacio Personal")) {
                c.setDefault();
                JavaFXMLApplication.setRoot(Paginas.ESPACIO_P);
            }

        });
        }

    }

    public static FXMLBusquedaUsuarioController getController() {
        return controlador;
    }

}
