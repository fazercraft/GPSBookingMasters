package tpgps.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tpgps.DbaseUtils;
import tpgps.model.Reserva;
import tpgps.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DelReserveController implements Initializable {


    @FXML
    Button btn_voltar;

    @FXML
    Label label_reserva;

    @FXML
    ChoiceBox<String> chooser_id;

    @FXML Button btn_remove;

    User alumni;
    ArrayList<Reserva> reservas;

    String disciplina="empty";
    Reserva resActiva = new Reserva("0","0","0","1");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        alumni = DbaseUtils.getAlumni();
        reservas = DbaseUtils.getReservas();
        label_reserva.setVisible(false);


        for (Reserva res: reservas) {
            if(res.getNomeUser().equalsIgnoreCase(alumni.getNameUser()) && res.getAtivo().contains("1")) {
                String booked = res.getDisciplinaUser();
                chooser_id.getItems().add(booked);
            }
        }


        chooser_id.getSelectionModel().selectedItemProperty().addListener(event -> {
            disciplina = chooser_id.getSelectionModel().getSelectedItem();
            for (Reserva res: reservas) {
                if(res.getNomeUser().equalsIgnoreCase(alumni.getNameUser()) && res.getAtivo().contains("1") && res.getDisciplinaUser().equalsIgnoreCase(disciplina)) {
                    label_reserva.setText(res.getDisciplinaUser() + " | " + res.getData() );
                    label_reserva.setVisible(true);
                    resActiva.setNomeUser(res.getNomeUser());
                    resActiva.setDisciplinaUser(res.getDisciplinaUser());
                    resActiva.setData(res.getData());
                    resActiva.setAtivo(res.getAtivo());
                    break;
                }
            }
        });


        btn_voltar.setOnAction(event -> {
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

        btn_remove.setOnAction(event -> {
            label_reserva.setVisible(false);

            // atualizar reservas -> deixa de estar ativa
            for(Reserva res : reservas){
                if(res.getNomeUser().equalsIgnoreCase(resActiva.getNomeUser()) &&
                        res.getDisciplinaUser().equalsIgnoreCase(resActiva.getDisciplinaUser())&&
                        res.getData().equalsIgnoreCase(resActiva.getData()) &&
                        res.getAtivo().equalsIgnoreCase(resActiva.getAtivo())){
                    res.setAtivo("0");
                    break;
                }
            }

            // apagar resrvas.txt e escrever para lá a lista de revs como está
            DbaseUtils.UpdateFileReservas();

            // atualizar no ficheiro bookingMastersCourses na discilipa o lugar para 0
            // onde o lugar for igual ao id do user
            DbaseUtils.removeLugar(disciplina);


            // depois disto a funcionar meter com o alert.CONFIRMATION e melhorar layout



        } );

    }
}
