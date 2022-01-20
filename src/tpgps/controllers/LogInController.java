package tpgps.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import tpgps.DbaseUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML private TextField txt_mail;
    @FXML private PasswordField txt_passAl;
    @FXML private Button btnLOGIN;
    @FXML private Pane idtem;
    @FXML private ImageView idIMG;
    @FXML private ImageView idIMGLeft;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        styleNanimes();
        setActions();
    }

    private void setActions() {


        // BTN LOGIN
        btnLOGIN.setOnAction(event -> DbaseUtils.logInUser(event,txt_mail.getText(),txt_passAl.getText()));
        btnLOGIN.setOnMouseEntered(mouseEvent -> btnLOGIN.setStyle("-fx-background-color: #9069e0"));
        btnLOGIN.setOnMouseExited(mouseEvent -> btnLOGIN.setStyle("-fx-background-color: #B96AF0"));
    }
    private void styleNanimes() {
        idtem.setStyle("-fx-background-radius: 20 20 20 20;\n" +
                       "-fx-border-radius: 20 20 20 20;\n" +
                       "-fx-background-color: #efe9e9;");


        FadeTransition trans = new FadeTransition(Duration.seconds(1), idIMG);
        trans.setFromValue(1.0);
        trans.setToValue(0.5);
        trans.setCycleCount(FadeTransition.INDEFINITE);
        trans.setAutoReverse(true);
        trans.play();
    }
}
