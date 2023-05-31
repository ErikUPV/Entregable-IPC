/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.registro;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafxmlapplication.JavaFXMLApplication;
import javafxmlapplication.Paginas;
import model.*;

/**
 * FXML Controller class
 *
 * @author erikb
 */
public class FXMLRegistroController implements Initializable {

    int cvc;
    @FXML
    private BorderPane borderPane;
    @FXML
    private VBox vBoxPrincipal;
    @FXML
    private ColumnConstraints columnaPrincipal;
    @FXML
    private ImageView perfilImageView;
    @FXML
    private TextField nickTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField tlfTextField;
    @FXML
    private PasswordField pwTextField;
    @FXML
    private PasswordField repeatPwTextField;
    @FXML
    private TextField creditCardTextField;
    @FXML
    private TextField cvcTextField;
    @FXML
    private Label badInputLabel;
    @FXML
    private Button registerButton;

    private Club club;

    boolean pwMatch;
    @FXML
    private GridPane labelsGridPane;
    @FXML
    private Label nickLabel;

    Image imagen;

    private boolean quiereSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        quiereSalir = true;
        pwMatch = true;
        // Listeners de los TextFields
        try {
            club = Club.getInstance();
        } catch (ClubDAOException | IOException e) {

        }

        nickTextField.requestFocus();

        repeatPwTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // When focus is lost from the second field
                String password1 = pwTextField.getText();
                String password2 = repeatPwTextField.getText();
                if (!password1.equals(password2)) {
                    showErrorMessage(pwTextField);
                    showErrorMessage(repeatPwTextField);
                    badInputLabel.setText("Passwords don't match");
                    repeatPwTextField.setText("");
                    badInputLabel.visibleProperty().setValue(true);
                    pwMatch = false;
                } else {
                    hideErrorMessage(pwTextField);
                    hideErrorMessage(repeatPwTextField);
                    badInputLabel.visibleProperty().setValue(false);
                    pwMatch = true;

                }
            }
        });

        cvcTextField.setTextFormatter(getTextFormatter(3));
        creditCardTextField.setTextFormatter(getTextFormatter(16));
        tlfTextField.setTextFormatter(getTextFormatter(9));

        repeatPwTextField.maxWidthProperty().bind(pwTextField.widthProperty());

        columnaPrincipal.minWidthProperty().set(400);
        // TODO
//          borderPane.widthProperty().addListener(
//                (observable, oldV, newV) -> {
//                    if (newV.intValue() <= 800) columnaPrincipal.maxWidthProperty().bind(columnaPrincipal.minWidthProperty());
//                    else columnaPrincipal.maxWidthProperty().bind(borderPane.widthProperty().multiply(0.3));
//                });

    }

    public static TextFormatter<String> getTextFormatter(int e) {
        TextFormatter<String> formatter2 = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0," + e + "}")) {
                return change;
            }
            return null;
        });
        return formatter2;
    }

    @FXML
    private void backButtonOnAction(ActionEvent event) {

        if (!nameTextField.getText().isEmpty() || !surnameTextField.getText().isEmpty() || !tlfTextField.getText().isEmpty() || !nickTextField.getText().isEmpty() || !pwTextField.getText().isEmpty() || !creditCardTextField.getText().isEmpty() || !cvcTextField.getText().isEmpty()) {

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

                } else {
                    JavaFXMLApplication.borrarTextField(nameTextField, nickTextField, pwTextField, repeatPwTextField, surnameTextField, tlfTextField, creditCardTextField, cvcTextField);
                    perfilImageView.setImage(null);
                    JavaFXMLApplication.setRoot(Paginas.INICIO);
                    hideErrorMessage(nameTextField);
                    hideErrorMessage(tlfTextField);
                    hideErrorMessage(creditCardTextField);
                    hideErrorMessage(surnameTextField);
                    hideErrorMessage(cvcTextField);
                    hideErrorMessage(nickTextField);
                    badInputLabel.setVisible(false);
                    JavaFXMLApplication.setRoot(Paginas.INICIO);
                }
            });
            return;
        }
        JavaFXMLApplication.setRoot(Paginas.INICIO);

    }

    @FXML
    private void elegirImagenOnAction(ActionEvent event) throws IOException {
        /*FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elegir imagen");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(
                ((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            Path imgPath = selectedFile.toPath();
            Image img = new Image(imgPath.toString());
            perfilImageView.setImage(img);
            imagen = img;
        }*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxmlapplication/registro/FXMLImagenes.fxml"));

        Parent p = loader.load();
        Scene scene = new Scene(p, 750, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMinHeight(600);
        stage.setMinWidth(750);
        stage.setMaxWidth(750);
        stage.setMaxHeight(600);
        stage.setTitle("Elección de imagen");
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLImagenesController controlador = loader.getController();

        stage.showAndWait();

        if (controlador.isOKPressed()) {
            perfilImageView.setImage(controlador.getImage());
        }
    }

    @FXML
    private void registerOnAction(ActionEvent event) throws IOException {

        if (nickTextField.textProperty().getValueSafe().isEmpty() || nickTextField.textProperty().getValueSafe().isBlank()) {
            showErrorMessage(nickTextField);
        } else {
            hideErrorMessage(nickTextField);
        }

        if (nameTextField.textProperty().getValueSafe().isEmpty() || nameTextField.textProperty().getValueSafe().isBlank()) {
            showErrorMessage(nameTextField);
        } else {
            hideErrorMessage(nameTextField);
        }

        if (surnameTextField.textProperty().getValueSafe().isEmpty()) {
            showErrorMessage(surnameTextField);
        } else {
            hideErrorMessage(surnameTextField);
        }

        if (tlfTextField.textProperty().getValueSafe().isEmpty()) {
            showErrorMessage(tlfTextField);
        } else {
            hideErrorMessage(tlfTextField);
        }

        if (pwTextField.textProperty().getValueSafe().isEmpty()) {
            showErrorMessage(pwTextField);
        } else if (!repeatPwTextField.getText().isEmpty()) {
            hideErrorMessage(pwTextField);
        }

        if (repeatPwTextField.textProperty().getValueSafe().isEmpty()) {
            showErrorMessage(repeatPwTextField);
        } else {
            hideErrorMessage(repeatPwTextField);
        }

        if ((creditCardTextField.textProperty().getValueSafe().isEmpty() && !cvcTextField.textProperty().getValueSafe().isEmpty()) || (!creditCardTextField.textProperty().getValueSafe().isEmpty() && cvcTextField.textProperty().getValueSafe().isEmpty())) {
            showErrorMessage(creditCardTextField);
            showErrorMessage(cvcTextField);

        } else {
            hideErrorMessage(creditCardTextField);
            hideErrorMessage(cvcTextField);
        }
        boolean b = nickTextField.getText().isEmpty()
                || nameTextField.getText().isEmpty()
                || surnameTextField.getText().isEmpty()
                || tlfTextField.getText().isEmpty()
                || pwTextField.getText().isEmpty()
                || (creditCardTextField.getText().isEmpty() && !cvcTextField.getText().isEmpty())
                || !pwMatch;
        if (!b) {
            try {
                if (tlfTextField.getText().length() != 9) {
                    showErrorMessage(tlfTextField);
                    badInputLabel.setText("Longitud insuficiente");

                }
                if (creditCardTextField.getText().length() < 16 && creditCardTextField.getText().length() > 0) {
                    badInputLabel.setText("Longitud insuficiente");
                    showErrorMessage(creditCardTextField);

                }
                if (cvcTextField.getText().length() < 3 && cvcTextField.getText().length() > 0) {
                    badInputLabel.setText("Longitud insuficiente");
                    showErrorMessage(cvcTextField);

                }
                if (tlfTextField.getText().length() != 9 || (cvcTextField.getText().length() < 3 && cvcTextField.getText().length() > 0) || (creditCardTextField.getText().length() < 16 && creditCardTextField.getText().length() > 0)) {
                    return;
                }
                System.out.println("usuario registrado");
                if (club.existsLogin(nickTextField.getText())) {
                    showErrorMessage(nickTextField);
                    badInputLabel.setText("Ya existe este usuario");
                    return;
                }

                if (cvcTextField.getText().isEmpty()) {
                    cvc = 0;
                } else {
                    cvc = Integer.parseInt(cvcTextField.getText());
                }
                club.registerMember(nameTextField.textProperty().getValue(), surnameTextField.textProperty().getValue(), tlfTextField.textProperty().getValue(),
                        nickTextField.textProperty().getValue(), pwTextField.textProperty().getValue(), creditCardTextField.textProperty().getValue(), cvc, perfilImageView.getImage());
            } catch (ClubDAOException e) {
                badInputLabel.textProperty().setValue("Valores Incorrectos");

            }
            JavaFXMLApplication.borrarTextField(nameTextField, nickTextField, surnameTextField, pwTextField, repeatPwTextField, tlfTextField, cvcTextField, creditCardTextField);
            perfilImageView.setImage(null);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0),
                            event2 -> { //FXMLLoader miCargador = JavaFXMLApplication.getLoader(Paginas.REGISTRO2);
//                                    Parent root = miCargador.getRoot();
//                                    if (root == null) try {
//                                        root = miCargador.load();

                                JavaFXMLApplication.setRoot(Paginas.REGISTRO2);
                            }),
                    new KeyFrame(Duration.seconds(1.5),
                            event2 -> {
                                JavaFXMLApplication.setRoot(Paginas.INICIO);
                                ;
                            })
            );
            timeline.setCycleCount(1);
            timeline.play();

        } else {
            badInputLabel.setText("Campos vacíos");
        }

    }

    private void showErrorMessage(TextField field) {
        String source = field.getId();
        badInputLabel.visibleProperty().setValue(true);
        field.styleProperty().setValue("-fx-background-color: #FCE5E0;"
                + "-fx-border-color: red;"
                + "-fx-border-radius: 5 5 5 5;"
                + "-fx-text-fill: red;"
                + "-fx-prompt-text-fill: red");

        //field.setPromptText(msg);
    }

    private void hideErrorMessage(TextField field) {

        field.styleProperty().setValue("");
    }

    private void startAlert(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../estilos.css").toExternalForm());
        dialogPane.getStyleClass().add("myAlert");
    }

}
