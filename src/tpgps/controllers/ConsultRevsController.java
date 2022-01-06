package tpgps.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tpgps.DbaseUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConsultRevsController implements Initializable {

    @FXML
    Button btn_voltaren;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_voltaren.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(DbaseUtils.class.getResource("views/menu.fxml"));
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
