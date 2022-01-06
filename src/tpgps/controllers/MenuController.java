package tpgps.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import tpgps.DbaseUtils;
import tpgps.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML Button btn_fazReserva;
    @FXML Button btn_removeReserva;
    @FXML Button btn_verReservas;
    @FXML Button btn_verLugares;
    @FXML Label txt_nomeAl;

    User alumni;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
           alumni = DbaseUtils.getAlumni();

           txt_nomeAl.setText(alumni.getNumUser());

           btn_fazReserva.setOnAction(event -> {
               Parent root = null;
               try {
                   root = FXMLLoader.load(DbaseUtils.class.getResource("views/reserveseat.fxml"));
               } catch (IOException exception) {
                   exception.printStackTrace();
               }
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.setTitle("Booking Masters");
               stage.setScene(new Scene(root,600,400));
               stage.show();

           } );





    }
}
