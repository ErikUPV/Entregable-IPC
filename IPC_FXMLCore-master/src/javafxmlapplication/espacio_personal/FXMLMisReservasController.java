package javafxmlapplication.espacio_personal;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import javafxmlapplication.autenticacion.FXMLAutenticacionController;
import static javafxmlapplication.autenticacion.FXMLAutenticacionController.cerrarSesion;
import javafxmlapplication.pistas.CourtDayItem;
import javafxmlapplication.pistas.FXMLVerPistasController;
import model.*;

/**
 * FXML Controller class
 *
 * @author Héctor
 */
public class FXMLMisReservasController implements Initializable {

    private static Member member;

    private Club club;
    private AnchorPane cambioAnchorPane;
    @FXML
    private TableView<Booking> reservasT;
    @FXML
    private VBox cambioVBOX;
    private String imagenPath;
    @FXML
    private TableColumn<Booking, String> col1;
    @FXML
    private TableColumn<Booking, String> col2;
    @FXML
    private TableColumn<Booking, String> col3;
    @FXML
    private TableColumn<Booking, String> col4;
    @FXML
    private TableColumn<Booking, String> col5;
    @FXML
    private TableColumn<Booking, String> col6;

    private ObservableList<Booking> reservaObsList;

    private List<Booking> reservaList;

    private static ObjectProperty memberProperty;
//    public void initMember(Member m) {
//
//        System.out.println(member.toString());
//        if (member.getImage() != null) {
//            profilePicture.setImage(member.getImage());
//        }
//        nameLabel.setText(member.getName() + " " + member.getSurname());
//
//    }
    @FXML
    private Button cancelar;

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    public void initMember(Member m) {
        member = m;
        reservaList = new ArrayList<>();
        reservaObsList = FXCollections.observableList(reservaList);
        reservaObsList.addAll(club.getUserBookings(member.getNickName()));
        reservasT.setItems(reservaObsList);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cancelar.requestFocus();
        JavaFXMLApplication.updatedProperty().addListener((ob, oldv, newv) -> {
           if (newv) {
               
               List<Booking> aux = club.getUserBookings(member.getNickName());
                
            
            for (Booking b : aux) {
                if (reservaObsList.indexOf(b) == -1) {
                    reservaObsList.add(b);
                }
            }
           }
        });
        System.out.println("hy");

        try {
            club = Club.getInstance();
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLMisReservasController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col1.maxWidthProperty().bind(reservasT.widthProperty().multiply(.195));
        col2.maxWidthProperty().bind(reservasT.widthProperty().multiply(.13));
        col3.maxWidthProperty().bind(reservasT.widthProperty().multiply(.13));
        col4.maxWidthProperty().bind(reservasT.widthProperty().multiply(.17));
        col5.maxWidthProperty().bind(reservasT.widthProperty().multiply(.18));
        col6.maxWidthProperty().bind(reservasT.widthProperty().multiply(.195));

        col1.minWidthProperty().bind(reservasT.widthProperty().multiply(.195));
        col2.minWidthProperty().bind(reservasT.widthProperty().multiply(.13));
        col3.minWidthProperty().bind(reservasT.widthProperty().multiply(.13));
        col4.minWidthProperty().bind(reservasT.widthProperty().multiply(.17));
        col5.minWidthProperty().bind(reservasT.widthProperty().multiply(.18));
        col6.minWidthProperty().bind(reservasT.widthProperty().multiply(.195));

        //col1.setCellValueFactory(new PropertyValueFactory<>("Dia"));
        //col2.setCellValueFactory(new PropertyValueFactory<>("Inicio"));
        //col3.setCellValueFactory(new PropertyValueFactory<>("Final"));
        //col4.setCellValueFactory(new PropertyValueFactory<>("Pista"));
        //col5.setCellValueFactory(new PropertyValueFactory<>("Pagado"));
        //col6.setCellValueFactory(new PropertyValueFactory<>("Cancelar"));
        //reservasT.setColumnResizePolicy((TableView.ResizeFeatures param) -> {
        //    if (param.getColumn() == null )return false;
        //    double delta = param.getDelta();
        //    ObservableList<TableColumn> cols = param.getTable().getColumns();
        //    int colIdx = cols.indexOf(param.getColumn());
        //  param.getColumn().setMinWidth(param.getColumn().getWidth() + delta);
        //  param.getColumn().setMaxWidth(param.getColumn().getWidth() + delta);
        //  if (colIdx < cols.size() -1){
        //      cols.get(colIdx+1).setMinWidth(cols.get(colIdx+1).getWidth() - delta);
        //      cols.get(colIdx+1).setMaxWidth(cols.get(colIdx+1).getWidth() - delta);
        //  } 
        //  return true;
        //});
        // Establecer la propiedad de ancho y alto del TableView para que se redimensione con el AnchorPane
        //reservasT.prefWidthProperty().bind(cambioAnchorPane.widthProperty());
        //reservasT.prefHeightProperty().bind(cambioAnchorPane.heightProperty());
        col1.setCellValueFactory(cellData -> {
            Booking item = cellData.getValue();
            String day = item.getBookingDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return new SimpleStringProperty(day);
        });

        col2.setCellValueFactory(cellData -> {
            Booking item = cellData.getValue();
            String hourF = item.getFromTime().toString();
            return new SimpleStringProperty(hourF);
        });

        col3.setCellValueFactory(cellData -> {
            Booking item = cellData.getValue();
            String hourT = item.getFromTime().plusHours(1).toString();
            return new SimpleStringProperty(hourT);
        });

        col4.setCellValueFactory(cellData -> {
            Booking item = cellData.getValue();
            String pista = item.getCourt().getName().substring(5);
            return new SimpleStringProperty(pista);
        });

        col5.setCellValueFactory(cellData -> {
            Booking item = cellData.getValue();
            String pagado = item.getPaid() ? "SI" : "NO";
            return new SimpleStringProperty(pagado);
        });

        col6.setCellValueFactory(cellData -> {
            Booking item = cellData.getValue();
            String cancelar;
            if (LocalDateTime.now().compareTo(item.getMadeForDay().atTime(item.getFromTime()).minusHours(24)) < 0) {
                cancelar = "SI";
            } else {
                cancelar = "NO";
            }
            return new SimpleStringProperty(cancelar);
        });
        //modificar pa image
//        col6.setCellValueFactory(cellData -> {
//            Booking item = cellData.getValue();
//            String cancelar;
//            if (LocalDateTime.now().compareTo(item.getMadeForDay().atTime(item.getFromTime()).minusHours(24))<0){
//                cancelar = "SI";} else {cancelar = "NO";}
//            return new SimpleStringProperty(cancelar);
//        });
        // 

    }

    @FXML
    private void cancelarButtonOnAction(ActionEvent event) {

    }
}
