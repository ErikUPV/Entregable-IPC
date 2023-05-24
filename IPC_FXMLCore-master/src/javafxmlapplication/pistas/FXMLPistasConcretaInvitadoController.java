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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
 * @author sergio
 */
public class FXMLPistasConcretaInvitadoController implements Initializable {

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
    //private ComboBox<String> comboBox;
    @FXML
    private Label dayLabel;

    private final String[] diaDeLaSemana = {"", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo"};

    private final String[] mesDelAño = {"", "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
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

    private boolean quiereReservar;
    @FXML
    private Button bReservar;
    @FXML
    private TableColumn<CourtDayItem, String> userCol;

    private ObjectProperty memberProperty;

    private List<String> comboList;
    private ObservableList<String> comboObsList;
    @FXML
    private Button volverPista;
    @FXML
    private VBox tableViewVBox;

    /**
     * Initializes the controller class.
     */
//    public void initPista(int pista) {
//        title.setText("Reservar pista " + pista);
//
//        File f;
//        switch (pista) {
//            case 1:
//                f = new File("src/javafxmlapplication/imagenes/courtImage-v1.png");
//                break;
//            case 2:
//                f = new File("src/javafxmlapplication/imagenes/courtImage-v2.png");
//                break;
//            case 3:
//                f = new File("src/javafxmlapplication/imagenes/courtImage-v3.png");
//                break;
//            case 4:
//                f = new File("src/javafxmlapplication/imagenes/courtImage-v4.png");
//                break;
//            case 5:
//                f = new File("src/javafxmlapplication/imagenes/courtImage-v5.png");
//                break;
//            default:
//                f = new File("src/javafxmlapplication/imagenes/courtImage-v6.png");
//                break;
//        }
//        Image i = new Image(f.toURI().toString());
//        imagenPista.setImage(i);
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println("nPista: " + nPista);
//        borderPane.widthProperty().addListener((observable, oldv, newv) -> {
//            if (newv.intValue() > 1200) {
//
//                );
////                mainVBox.minWidthProperty().setValue(1200);
////            } else {
////                mainVBox.minWidthProperty().setValue(600);
////
//            }
//        });
        mainVBox.maxWidthProperty().bind(borderPane.widthProperty().multiply(0.9));

        try {
            // TODO
            club = Club.getInstance();
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLPistaConcretaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLVerPistasController.numPista.addListener((ob, oldv, newv) -> {
            title.setText("Reservar pista " + newv);

            File f;
            switch (newv.intValue()) {
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

            nPista = newv.intValue();
            court = club.getCourt("Pista " + newv.intValue());
            System.out.println("Court: " + court.getName());
            updateTableView(LocalDate.now());
            datePicker.valueProperty().setValue(LocalDate.now());
            datePicker.disableProperty().setValue(true);
            bReservar.disableProperty().setValue(true);
        });
        memberProperty = FXMLAutenticacionController.memberProperty();

        //memberProperty.addListener((ob, oldv, newv) -> {
        //    member = (Member) newv;
        //    comboBox.promptTextProperty().setValue(member.getName() + " " + member.getSurname());
        //    System.out.println(member.getName() + " " + member.getSurname());
        //});

        //comboList = new ArrayList<String>();

        //comboObsList = FXCollections.observableArrayList(comboList);
        //comboObsList.addAll("Mis reservas", "Cerrar sesión");

        //comboBox.setItems(comboObsList);
        //comboBox.setCellFactory(c -> new ComboListCell());

        mainVBox.maxHeightProperty().bind(borderPane.heightProperty().multiply(0.8));
        borderPane.heightProperty().addListener((observable, oldv, newv) -> {
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
        //datePicker.valueProperty().setValue(LocalDate.now());

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

        userCol.setCellValueFactory(cellData -> {
            CourtDayItem item = cellData.getValue();
            boolean status = item.getStatus();
            if (status == CourtDayItem.OCUPADO) {
                return new SimpleStringProperty(item.getUser().getNickName());
            }
            return new SimpleStringProperty("");
        });

        pistaTableView.setRowFactory(tv -> new TableRow<CourtDayItem>() {
            @Override
            protected void updateItem(CourtDayItem item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else {
                    if (!item.getStatus()) {
                        setStyle("-fx-background-color: rgb(146, 199, 169);");
                    } else {
                        setStyle("");
                    }
                }
            }

        });

        l = FXCollections.observableArrayList();

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

        pistaTableView.getSelectionModel().selectedItemProperty().addListener((ob, oldv, newv) -> {
            if (newv == null) {
                bReservar.disableProperty().setValue(true);
                return;
            }
            if (newv.getStatus() == CourtDayItem.OCUPADO) {
                bReservar.disableProperty().setValue(true);
            } else {
                bReservar.disableProperty().setValue(false);
            }
        });

        horaCol.prefWidthProperty().bind(pistaTableView.widthProperty().multiply(0.2));
        estadoCol.prefWidthProperty().bind(pistaTableView.widthProperty().multiply(0.4));
        userCol.prefWidthProperty().bind(pistaTableView.widthProperty().multiply(0.399));

        //comboBox.getSelectionModel().selectedItemProperty().addListener((ob, oldv, newv) -> {
        //    if (newv == null) return;
        //    if (newv.equals("Cerrar sesión")) {
        //        FXMLAutenticacionController.cerrarSesion();

        //    } else if (newv.equals("Mis reservas")) {
        //        JavaFXMLApplication.setRoot(Paginas.ESPACIO_PERSONAL);
        //    }

        //});

    }

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
//        FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.PISTAS);
//        Parent root = miCargador.getRoot();
//        if (root == null) {
//            root = miCargador.load();
//        }
        JavaFXMLApplication.setRoot(Paginas.PISTAS_INVITADO);

    }

    private void updateTableView(LocalDate selectedDate) {
        list = club.getCourtBookings(court.getName(), selectedDate);

        ObservableList<CourtDayItem> filteredData = FXCollections.observableArrayList();
        for (int i = 0; i <= 12; i++) {
            int b = 9;
            filteredData.add(new CourtDayItem(selectedDate, LocalTime.of(b + i, 0), CourtDayItem.LIBRE));

        }
        l = FXCollections.observableArrayList(filteredData);
        checkAvaliability();
        pistaTableView.setItems(filteredData);

    }

    @FXML
    private void reservarButtonOnAction(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        startAlert(alert);
        alert.setHeaderText("Reservar pista");
        alert.setContentText("¿Está seguro de que quiere reservar la pista?");
        Optional<ButtonType> res;
        res = alert.showAndWait();
        res.ifPresent(e -> {
            if (!e.equals(ButtonType.OK)) {
                quiereReservar = false;
            } else {
                quiereReservar = true;
            }
        });
        if (!quiereReservar) {
            return;
        }
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
        if (!creditCard.isEmpty()) {
            paid = true;
        }
        checkAvaliability();
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
//                System.out.println(t);
//                System.out.println("System booking: " + e.getBookingDate());
                if (t.equals(e.getMadeForDay().atTime(e.getFromTime())) && court.equals(e.getCourt())) {
                    elementObs.setStatus(CourtDayItem.OCUPADO);
                    elementObs.setUser(e.getMember());
                }
            });

        });
        pistaTableView.refresh();

    }

    private void startAlert(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../estilos.css").toExternalForm());
        dialogPane.getStyleClass().add("myAlert");
    }

}

//class ComboListCell<String> extends ListCell<String> {

//    protected void updateItem(String s, boolean empty) {
//      super.updateItem(s, empty);

//        if (empty || s == null) {
//            setText(null);
//        } else {
//            setText(s.toString());
//            setStyle("-fx-underline: true;");
//        }
//    }
//}