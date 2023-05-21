/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.pistas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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
    private Integer nPista;
    @FXML
    private Label title;
    @FXML
    private VBox mainVBox;
    @FXML
    private BorderPane borderPane;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<CourtDayItem> pistaTableView;
    
    private Member member = null;
    
    private Club club;
    @FXML
    private ComboBox<?> comboBox;
    @FXML
    private Label dayLabel;
    
    private final String[] diaDeLaSemana = {"", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo"};
    
    private final String[] mesDelAño = {"","enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
    @FXML
    private VBox dibujoVBox;
    
    private ObservableList<String> listaObs; 
    @FXML
    private TableColumn<CourtDayItem, String> horaCol;
    @FXML
    private TableColumn<CourtDayItem, String> estadoCol;
    
    private ObservableList<CourtDayItem> l;
    
    private List<Booking> list;
    
    private Court court;
    
    private IntegerProperty nPistaProperty;
    /**
     * Initializes the controller class.
     */
  
    public void initPista(int pista) {
        title.setText("Reservar pista " + pista);
        
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
        
        
      
        System.out.println("nPista: " + nPista);
        borderPane.widthProperty().addListener( (observable, oldv, newv) -> {
            if (newv.intValue() > 1200) {
               
                mainVBox.maxWidthProperty().bind(borderPane.widthProperty().multiply(0.6));
                mainVBox.minWidthProperty().setValue(1200);
            } else {
                mainVBox.maxWidthProperty().bind(borderPane.widthProperty());
                mainVBox.minWidthProperty().setValue(800);
                
            }
        });
        
        member = FXMLAutenticacionController.getMember();
        System.out.println("pista concreta: " + member.getNickName());
        try {
            // TODO
            club = Club.getInstance();
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLPistaConcretaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          FXMLVerPistasController.numPista.addListener((ob, oldv, newv) -> {
              nPista = newv.intValue();
              court = club.getCourt("Pista " + newv.intValue());
              System.out.println("Court: " + court.getName());
              updateTableView(LocalDate.now());
          });
        
        
        
        comboBox.setPromptText(member.getName() + " " + member.getSurname());
        
         borderPane.heightProperty().addListener( (observable, oldv, newv) -> {
            if (newv.intValue() > 700) {
                dibujoVBox.alignmentProperty().set(Pos.BASELINE_CENTER);
            pistaTableView.minHeightProperty().bind(mainVBox.heightProperty().multiply(0.67));
            
            } else {
                dibujoVBox.alignmentProperty().set(Pos.TOP_CENTER);
                pistaTableView.minHeightProperty().unbind();
                pistaTableView.setMinHeight(300);
            }
        });
        
        
//        borderPane.heightProperty().addListener((ob, oldv, newv)-> {
//           if (newv.intValue() > 700) {
//               
//           } else {
//               dibujoVBox.alignmentProperty().set(Pos.TOP_CENTER);
//           }
//        });


        datePicker.valueProperty().setValue(LocalDate.now());
        
        
        
        
        
         horaCol.setCellValueFactory(cellData -> {
            CourtDayItem item = cellData.getValue();
            String hour = item.getFromTime().toString();
            return new SimpleStringProperty(hour);
        });

        estadoCol.setCellValueFactory(cellData -> {
            CourtDayItem item = cellData.getValue();
            String status = item.getStatus() ? "Libre" : "Reservada";
            return new SimpleStringProperty(status);
        });
         l = FXCollections.observableArrayList(
                
                
        );
         
        
        
       
        
        
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateTableView(newValue);
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
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        datePicker.valueProperty().addListener((ob, oldv, newv) -> {
            int numDayNow = newv.get(weekFields.dayOfWeek());
            int month = newv.getMonthValue();
            dayLabel.textProperty().setValue("Pistas disponibles a " + diaDeLaSemana[numDayNow] + ", " + newv.getDayOfMonth() + " de " + mesDelAño[month] + " del " + newv.getYear());
        });
        
    }        

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTAS);
        Parent root = miCargador.getRoot();
        if (root == null) root = miCargador.load();
        JavaFXMLApplication.setRoot(root);
        
    }
    
    
    private void updateTableView(LocalDate selectedDate) {
        list = club.getCourtBookings("Pista " + nPista, selectedDate);
        
        ObservableList<CourtDayItem> filteredData = FXCollections.observableArrayList();
         for (int i = 0; i <= 12; i++) {
            int b = 9;
            filteredData.add(new CourtDayItem(selectedDate, LocalTime.of(b + i, 0), true));
                
        }
        l.setAll(filteredData);
        checkAvaliability();
        pistaTableView.setItems(filteredData);
        
        
    }

    @FXML
    private void reservarButtonOnAction(ActionEvent event) {
        CourtDayItem aReservar = pistaTableView.getSelectionModel().getSelectedItem();
        LocalDate dia = aReservar.getMadeForDay();
        System.out.println("Dia: " + dia);
        LocalTime time = aReservar.getFromTime();
        System.out.println(dia + " " + time);
        LocalDateTime diaYHora = dia.atTime(time);
        System.out.println("LocalDateTime: " + dia + " " + time);
        String creditCard = member.getCreditCard();
        System.out.println("Credit card: " + creditCard);
        boolean paid = false;
        List<Court> courts = club.getCourts();
        System.out.println("Num pista: " + nPista);
        if (!creditCard.isEmpty()) paid = true;
       
        try {
            club.registerBooking(diaYHora, dia, time, paid, court, member);
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLPistaConcretaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        

        updateTableView(dia);
         
       
    }
    
    
    private void checkAvaliability() {
         l.forEach(elementObs -> {
             LocalDateTime t = elementObs.getMadeForDay().atTime(elementObs.getFromTime());
            list.forEach(e -> {
                if (t.equals(e.getBookingDate()) && court.equals(e.getCourt())) {
                    elementObs.setStatus(CourtDayItem.OCUPADO);
                } 
        });
           
        });
                  pistaTableView.refresh();

    }

}
