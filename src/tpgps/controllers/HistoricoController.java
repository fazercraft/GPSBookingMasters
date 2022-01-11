package tpgps.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import tpgps.DbaseUtils;
import tpgps.model.Reserva;
import tpgps.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class HistoricoController implements Initializable {

    @FXML
    Button btn_voltar;

    @FXML
    ListView<String> list_view_id;

    @FXML
    Label label_reservas_encontradas;

    User alumni;
    ArrayList<Reserva> reservas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        alumni = DbaseUtils.getAlumni();
        reservas = DbaseUtils.getReservas();
        int num = 0;

        for (Reserva res: reservas) {
            if(res.getNomeUser().equalsIgnoreCase(alumni.getNameUser())) {
                String booked = res.getDisciplinaUser() + " | " + res.getData() + " | " + res.getAtivo();
                list_view_id.getItems().add(booked);
                num++;
            }

        }

        label_reservas_encontradas.setText(String.valueOf(num));






        btn_voltar.setOnAction(event -> {
            Parent root;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(DbaseUtils.class.getResource("views/menu.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Booking Masters");
                stage.setScene(new Scene(root,600,400));
                stage.show();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

    }
}
