package tpgps.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tpgps.DbaseUtils;
import tpgps.model.Reserva;
import tpgps.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;


public class DelReserveController implements Initializable {

    @FXML Pane idtem;
    @FXML Button btn_voltar;
    @FXML Label label_reserva;
    @FXML ChoiceBox<String> chooser_id;
    @FXML Button btn_remove;
    @FXML ImageView idIMG;

    User alumni;
    ArrayList<Reserva> reservas;

    String disciplina="empty";
    Reserva resActiva = new Reserva("0","0","0","1");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        styleNanimes();

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
                    label_reserva.setText(res.getData() + " | " + res.getDisciplinaUser() );
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
                root = FXMLLoader.load(Objects.requireNonNull(DbaseUtils.class.getResource("views/menu.fxml")));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Booking Masters");
            stage.setScene(new Scene(Objects.requireNonNull(root),600,400));
            stage.show();
        } );
        btn_remove.setOnAction(event -> {
            if(!disciplina.equalsIgnoreCase("empty")){
                // atualizar reservas -> deixa de estar ativa
                Optional<ButtonType> result  = alertaConfimacao();
                if(!result.isPresent() || result.get() == ButtonType.OK){
                    label_reserva.setVisible(false);
                    for(Reserva res : reservas){
                        if(res.getNomeUser().equalsIgnoreCase(resActiva.getNomeUser()) &&
                                res.getDisciplinaUser().equalsIgnoreCase(resActiva.getDisciplinaUser())&&
                                res.getData().equalsIgnoreCase(resActiva.getData()) &&
                                res.getAtivo().equalsIgnoreCase(resActiva.getAtivo())){
                            res.setAtivo("0");
                            break;
                        }
                    }
                    DbaseUtils.UpdateFileReservas();
                    DbaseUtils.removeLugar(disciplina);
                    chooser_id.getSelectionModel().clearSelection();
                    chooser_id.getItems().remove(disciplina);
                }
            }else{ alertaErro(); }
        } );

    }

    private void styleNanimes() {
        idtem.setStyle("-fx-background-radius: 20 20 20 20;\n" +
                "    -fx-border-radius: 20 20 20 20;\n" +
                "    -fx-background-color: #efe9e9;");

        FadeTransition trans = new FadeTransition(Duration.seconds(1), idIMG);
        trans.setFromValue(1.0);
        trans.setToValue(0.5);
        trans.setCycleCount(FadeTransition.INDEFINITE);
        trans.setAutoReverse(true);
        trans.play();

        btn_remove.setOnMouseEntered(mouseEvent -> btn_remove.setStyle("-fx-background-color: #9069e0"));
        btn_remove.setOnMouseExited(mouseEvent -> btn_remove.setStyle("-fx-background-color: #B96AF0"));
        btn_voltar.setOnMouseEntered(mouseEvent -> btn_voltar.setStyle("-fx-background-color: #9069e0"));
        btn_voltar.setOnMouseExited(mouseEvent -> btn_voltar.setStyle("-fx-background-color: #B96AF0"));
    }
    private void alertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Reserva inválida");
        alert.setContentText("Seleciona uma reserva primeiro");
        alert.show();
    }
    private Optional<ButtonType> alertaConfimacao() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirme");
        alert.setContentText("Tem a certeza de que deseja remover lugar/reserva?");
        return alert.showAndWait();
    }
}
