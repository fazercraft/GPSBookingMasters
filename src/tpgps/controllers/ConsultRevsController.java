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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tpgps.DbaseUtils;
import tpgps.model.Reserva;
import tpgps.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConsultRevsController implements Initializable {

    @FXML
    Button btn_voltaren;
    @FXML
    ListView<String> consulta_list_view;
    @FXML
    Label label_reservas_encontradas;
    @FXML private Pane idtem;
    User alumni;
    ArrayList<Reserva> reservas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        alumni = DbaseUtils.getAlumni();
        reservas=DbaseUtils.getReservas();

        idtem.setStyle("-fx-background-radius: 20 20 20 20;\n" +
                "    -fx-border-radius: 20 20 20 20;\n" +
                "    -fx-background-color: #efe9e9;");


        for (Reserva res: reservas) {
            if(res.getNomeUser().equalsIgnoreCase(alumni.getNameUser()) && res.getAtivo().contains("1")) {
                String booked = res.getDisciplinaUser() + " | " + res.getData() + " | " + res.getAtivo();
                consulta_list_view.getItems().add(booked);
            }
        }
        label_reservas_encontradas.setText(String.valueOf(consulta_list_view.getItems().size()));

        btn_voltaren.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(DbaseUtils.class.getResource("views/menu.fxml")));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Booking Masters");
            stage.setScene(new Scene(Objects.requireNonNull(root),600,400));
            stage.show();

        } );
        btn_voltaren.setOnMouseEntered(mouseEvent -> btn_voltaren.setStyle("-fx-background-color: #9069e0"));
        btn_voltaren.setOnMouseExited(mouseEvent -> btn_voltaren.setStyle("-fx-background-color: #B96AF0"));
    }
}
