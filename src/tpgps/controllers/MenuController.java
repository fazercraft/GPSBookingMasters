package tpgps.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tpgps.DbaseUtils;
import tpgps.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML Button btn_fazReserva;
    @FXML Button btn_removeReserva;
    @FXML Button btn_verReservas;
    @FXML Button btn_historico;
    @FXML Label txt_nomeAl;
    @FXML private Pane idtem;

    User alumni;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
           alumni = DbaseUtils.getAlumni();

           txt_nomeAl.setText(alumni.getNumUser());


        idtem.setStyle("-fx-background-radius: 20 20 20 20;\n" +
                "    -fx-border-radius: 20 20 20 20;\n" +
                "    -fx-background-color: #efe9e9;");

           btn_fazReserva.setOnAction(event -> {
               Parent root = null;
               try {
                   root = FXMLLoader.load(Objects.requireNonNull(DbaseUtils.class.getResource("views/reserveseat.fxml")));
               } catch (IOException exception) {
                   exception.printStackTrace();
               }
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.setTitle("Booking Masters");
               stage.setScene(new Scene(Objects.requireNonNull(root),600,400));
               stage.show();

           } );
           btn_removeReserva.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(DbaseUtils.class.getResource("views/removeseat.fxml")));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Booking Masters");
            stage.setScene(new Scene(Objects.requireNonNull(root),600,400));
            stage.show();

        } );
           btn_verReservas.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(DbaseUtils.class.getResource("views/consultrevs.fxml")));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Booking Masters");
            stage.setScene(new Scene(Objects.requireNonNull(root),600,400));
            stage.show();

        } );
           btn_historico.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(DbaseUtils.class.getResource("views/historico.fxml")));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Booking Masters");
            stage.setScene(new Scene(Objects.requireNonNull(root),600,400));
            stage.show();

        } );


        btn_fazReserva.setOnMouseEntered(mouseEvent -> btn_fazReserva.setStyle("-fx-background-color: #9069e0"));
        btn_fazReserva.setOnMouseExited(mouseEvent -> btn_fazReserva.setStyle("-fx-background-color: #B96AF0"));

        btn_removeReserva.setOnMouseEntered(mouseEvent -> btn_removeReserva.setStyle("-fx-background-color: #9069e0"));
        btn_removeReserva.setOnMouseExited(mouseEvent -> btn_removeReserva.setStyle("-fx-background-color: #B96AF0"));

        btn_verReservas.setOnMouseEntered(mouseEvent -> btn_verReservas.setStyle("-fx-background-color: #9069e0"));
        btn_verReservas.setOnMouseExited(mouseEvent -> btn_verReservas.setStyle("-fx-background-color: #B96AF0"));

        btn_historico.setOnMouseEntered(mouseEvent -> btn_historico.setStyle("-fx-background-color: #9069e0"));
        btn_historico.setOnMouseExited(mouseEvent -> btn_historico.setStyle("-fx-background-color: #B96AF0"));


    }
}
