package tpgps.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tpgps.DbaseUtils;
import tpgps.model.User;

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

           //DEBUG
        System.out.println( "Aluno: " + alumni.getNameUser());

           txt_nomeAl.setText(alumni.getNameUser());


    }
}
