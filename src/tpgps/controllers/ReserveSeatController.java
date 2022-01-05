package tpgps.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import tpgps.DbaseUtils;
import tpgps.model.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ReserveSeatController implements Initializable {

    User alumni;

    @FXML
    ChoiceBox<String> idChooser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        alumni = DbaseUtils.getAlumni();
        ArrayList<String> listaDisciplinas = new ArrayList<>();

        idChooser.getItems().add("AMOV");

    }
}