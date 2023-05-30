/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.espacio_personal;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.util.Pair;
import javafxmlapplication.autenticacion.FXMLAutenticacionController;
import javafxmlapplication.registro.FXMLRegistroController;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Héctor
 */
public class FXMLModificarDatosController implements Initializable {

    @FXML
    private VBox cambioVBOX;
    @FXML
    private Label badInputLabel;
    @FXML
    private Button modificar;
    private Member member;
    private Club club;
    @FXML
    private Button editarNombre;
    @FXML
    private Button editarTelefono;
    @FXML
    private Button editarContraseña;
    @FXML
    private Button editarTarjeta;
    @FXML
    private Button editarImagen;
    @FXML
    private ImageView profilePicture;
    private static ObjectProperty memberProperty;
    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label tlfLabel;
    @FXML
    private Label cardLabel;
    @FXML
    private Label cvcLabel;
    @FXML
    private Label passwordLabel;
    private String password;

    private boolean cambiado;

    private boolean quiereSalir;

    FXMLEspacioPController controlador;

    String name;
    String surname;
    String telefono;
    String cardNumber;
    int cvc;
    Image img;
    @FXML
    private VBox imageVBox;
    @FXML
    private VBox mainVBox;

    String style;

    public void initMember(Member m, FXMLEspacioPController c) throws ClubDAOException, IOException {
        member = m;
        club = Club.getInstance();
        profilePicture.setImage(member.getImage());
        nameLabel.setText(member.getName());
        surnameLabel.setText(member.getSurname());
        cardLabel.setText(censurar(member.getCreditCard(), false));
        if (member.getSvc() != 0) cvcLabel.setText("" + member.getSvc());
        else cvcLabel.setText("");
        tlfLabel.setText(member.getTelephone());
        passwordLabel.setText(censurar(member.getPassword(), true));
        password = member.getPassword();
        editarNombre.requestFocus();
        controlador = c;

        name = member.getName();
        surname = member.getSurname();
        telefono = member.getTelephone();
        cardNumber = member.getCreditCard();
        cvc = member.getSvc();
        img = member.getImage();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cambiado = false;
        editarNombre.requestFocus();
        quiereSalir = true;
        style = nameLabel.getStyle();
        profilePicture.fitWidthProperty().bind(imageVBox.widthProperty().multiply(0.4));

        mainVBox.widthProperty().addListener((ob, oldv, newv) -> {
            if (newv.intValue() < 900) {
                nameLabel.setStyle("-fx-font-size: 17");
                surnameLabel.setStyle("-fx-font-size: 17");
                tlfLabel.setStyle("-fx-font-size: 17");
                passwordLabel.setStyle("-fx-font-size: 17");
                cardLabel.setStyle("-fx-font-size: 17");
                cvcLabel.setStyle("-fx-font-size: 17");
            } else {
                nameLabel.setStyle(style);
                surnameLabel.setStyle(style);
                tlfLabel.setStyle(style);
                passwordLabel.setStyle(style);
                cardLabel.setStyle(style);
                cvcLabel.setStyle(style);
            }
        });
    }

    @FXML
    private void modificarOnAction(ActionEvent event) {
        cambiado = false;

        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setTitle("Confirmación");
        startAlert(a);
        a.setHeaderText("¿Está seguro de que quiere realizar estos cambios?");
        a.setContentText("Podrá modificarlos en cualquier momento");
        startAlert(a);
        Optional<ButtonType> res;
        res = a.showAndWait();
        res.ifPresent(e -> {
            quiereSalir = e.equals(ButtonType.OK);
            if (quiereSalir) {
                member.setName(name);
                member.setSurname(surname);
                member.setTelephone(telefono);
                member.setPassword(password);
                member.setCreditCard(cardNumber);
                member.setSvc(cvc);
                member.setImage(img);
                controlador.updateItems();
            }
        });

    }

    @FXML
    private void editarNombreOnAction(ActionEvent event) {
        quiereSalir = false;
        TextField nameField = new TextField();
        TextField surnameField = new TextField();
        Label l1 = new Label("Nombre");
        Label l2 = new Label("Apellidos");

        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        hBox2.setAlignment(Pos.CENTER_RIGHT);
        hBox1.setSpacing(10);
        hBox2.setSpacing(10);
        hBox1.setPadding(new Insets(0, 10, 0, 0));
        hBox2.setPadding(new Insets(0, 10, 0, 0));
        hBox1.getChildren().addAll(l1, nameField);
        hBox2.getChildren().addAll(l2, surnameField);
        hBox1.setPrefWidth(Region.USE_COMPUTED_SIZE);
        hBox2.setPrefWidth(Region.USE_COMPUTED_SIZE);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBox1, hBox2);
        vBox.setPadding(new Insets(10, 50, 10, 0));

        Dialog<Boolean> dialog = new Dialog();
        dialog.setTitle("Cambiar nombre");
        dialog.setHeaderText("ATENCIÓN\nAl aceptar se cambiarán los datos modificados");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(vBox);
        startAlert(dialog);

        dialog.setResultConverter((buttonType) -> {
            if (buttonType == ButtonType.OK) {
                return true;
            }
            return false;
        });

        dialog.showAndWait();

        if (dialog.getResult()) {
            if (nameField.getText().equals("") || surnameField.getText().equals("")) {
                alertaError();
                editarNombreOnAction(event);
            } else {
                name = nameField.getText();
                surname = surnameField.getText();
                nameLabel.setText(name);
                surnameLabel.setText(surname);

                todoBien();
            }
        }
    }

    @FXML
    private void editarTelefonoOnAction(ActionEvent event) {
        quiereSalir = false;
        TextField tlfField = new TextField();
        Label l1 = new Label("Teléfono");
        tlfField.setTextFormatter(FXMLRegistroController.getTextFormatter(9));

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        hBox1.setSpacing(10);
        hBox1.setPadding(new Insets(0, 10, 0, 0));
        hBox1.getChildren().addAll(l1, tlfField);
        hBox1.setPrefWidth(Region.USE_COMPUTED_SIZE);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBox1);
        vBox.setPadding(new Insets(10, 50, 10, 0));

        Dialog<Boolean> dialog = new Dialog();
        dialog.setTitle("Cambiar teléfono");
        dialog.setHeaderText("ATENCIÓN\nAl aceptar se cambiarán los datos modificados");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(vBox);
        startAlert(dialog);

        dialog.setResultConverter((buttonType) -> {
            if (buttonType == ButtonType.OK) {
                return true;
            }
            return false;
        });

        dialog.showAndWait();

        if (dialog.getResult()) {
            if (tlfField.getText().equals("") || tlfField.getText().length() != 9 || !tlfField.getText().matches("[0-9]+")) {
                alertaError();
                editarTelefonoOnAction(event);
            } else {
                telefono = tlfField.getText();

                tlfLabel.setText(tlfField.getText());
                todoBien();
            }
        }
    }

    @FXML
    private void editarContraseñaOnAction(ActionEvent event) {
        quiereSalir = false;
        PasswordField passwordField = new PasswordField();
        PasswordField repPasswordField = new PasswordField();
        Label l1 = new Label("Contraseña");
        Label l2 = new Label("Repetir contraseña");

        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        hBox2.setAlignment(Pos.CENTER_RIGHT);
        hBox1.setSpacing(10);
        hBox2.setSpacing(10);
        hBox1.setPadding(new Insets(0, 10, 0, 0));
        hBox2.setPadding(new Insets(0, 10, 0, 0));
        hBox1.getChildren().addAll(l1, passwordField);
        hBox2.getChildren().addAll(l2, repPasswordField);
        hBox1.setPrefWidth(Region.USE_COMPUTED_SIZE);
        hBox2.setPrefWidth(Region.USE_COMPUTED_SIZE);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBox1, hBox2);
        vBox.setPadding(new Insets(10, 30, 10, 0));

        Dialog<Boolean> dialog = new Dialog();
        dialog.setTitle("Cambiar contraseña");
        dialog.setHeaderText("ATENCIÓN\nAl aceptar se cambiarán los datos modificados");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(vBox);
        startAlert(dialog);

        dialog.setResultConverter((buttonType) -> {
            if (buttonType == ButtonType.OK) {
                return true;
            }
            return false;
        });

        dialog.showAndWait();

        if (dialog.getResult()) {
            if (passwordField.getText().equals("") || repPasswordField.getText().equals("") || !passwordField.getText().equals(repPasswordField.getText())) {
                alertaError();
                editarContraseñaOnAction(event);
            } else {
                password = passwordField.getText();
                passwordLabel.setText(censurar(password, true));
                todoBien();
            }
        }
    }

    @FXML
    private void editarTarjetaOnAction(ActionEvent event) {
        quiereSalir = false;
        TextField cardField = new TextField();
        TextField cvcField = new TextField();

        cardField.setTextFormatter(FXMLRegistroController.getTextFormatter(16));
        cvcField.setTextFormatter(FXMLRegistroController.getTextFormatter(3));

        Label l1 = new Label("Número de tarjeta");
        Label l2 = new Label("CVC");

        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        hBox2.setAlignment(Pos.CENTER_RIGHT);
        hBox1.setSpacing(10);
        hBox2.setSpacing(10);
        hBox1.setPadding(new Insets(0, 10, 0, 0));
        hBox2.setPadding(new Insets(0, 10, 0, 0));
        hBox1.getChildren().addAll(l1, cardField);
        hBox2.getChildren().addAll(l2, cvcField);
        hBox1.setPrefWidth(Region.USE_COMPUTED_SIZE);
        hBox2.setPrefWidth(Region.USE_COMPUTED_SIZE);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBox1, hBox2);
        vBox.setPadding(new Insets(10, 30, 10, 0));

        Dialog<Boolean> dialog = new Dialog();
        dialog.setTitle("Cambiar nombre");
        dialog.setHeaderText("ATENCIÓN\nAl aceptar se cambiarán los datos modificados");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(vBox);
        startAlert(dialog);

        dialog.setResultConverter((buttonType) -> {
            if (buttonType == ButtonType.OK) {
                return true;
            }
            return false;
        });

        dialog.showAndWait();

        if (dialog.getResult()) {
            if (cardField.getText().equals("") || cvcField.getText().equals("") || cvcField.getText().length() != 3 || cardField.getText().length() != 16) {
                alertaError();
                //editarTarjetaOnAction(event);
            } else {
                cardNumber = cardField.getText();
                cvc = Integer.parseInt(cvcField.getText());

                cardLabel.setText(censurar(cardField.getText(), false));
                cvcLabel.setText(cvcField.getText());
                todoBien();
            }
        }
    }

    @FXML
    private void editarImagenOnAction(ActionEvent event) {
        quiereSalir = false;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elegir imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(
                ((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            Path imgPath = selectedFile.toPath();
            img = new Image(imgPath.toString());
            profilePicture.setImage(img);

        }

    }

    private void alertaError() {
        Alert a = new Alert(AlertType.ERROR);
        a.setTitle("Error");
        a.setHeaderText("Ha ocurrido un error");
        a.setContentText("Los valores introducidos no son válidos.");
        startAlert(a);
        a.showAndWait();
    }

    private void todoBien() {
        Alert a = new Alert(AlertType.INFORMATION);
        a.setTitle("Confirmación");
        a.setHeaderText("Todo ha salido según lo planeado");
        a.setContentText("Los cambios han sido realizados correctamente.");
        startAlert(a);
        a.showAndWait();
        cambiado = true;
    }

    private String censurar(String s, boolean todo) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            if (!todo && i == s.length() - 4) {
                return res += s.substring(i);
            } else {
                res += "*";
            }
        }
        return res;
    }

    private void startAlert(Dialog alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../estilos.css").toExternalForm());
        dialogPane.getStyleClass().add("myAlert");
    }

    public boolean confirmaCambiados() {
        if (quiereSalir) {
            return true;
        }
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setTitle("Confirmación");
        startAlert(a);
        a.setHeaderText("¿Quiere salir sin guardar?");
        a.setContentText("Si sale ahora sus cambios no se guardarán");
        startAlert(a);
        Optional<ButtonType> res;
        res = a.showAndWait();
        res.ifPresent(e -> {
            if (!e.equals(ButtonType.OK)) {
                quiereSalir = false;
            } else {
                quiereSalir = true;
            }
        });
        return quiereSalir;
    }

}
