package tpgps.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tpgps.DbaseUtils;
import tpgps.model.Reserva;
import tpgps.model.User;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


public class ReserveSeatController implements Initializable {

    User alumni;
    ArrayList<Reserva> reservas;

    @FXML ChoiceBox<String> idChooser;
    @FXML Button btn_voltar;

    @FXML ImageView l1; @FXML ImageView l2; @FXML ImageView l3; @FXML ImageView l4; @FXML ImageView l5;
    @FXML ImageView l6; @FXML ImageView l7; @FXML ImageView l8; @FXML ImageView l9; @FXML ImageView l10;
    @FXML ImageView l11; @FXML ImageView l12; @FXML ImageView l13; @FXML ImageView l14; @FXML ImageView l15;
    @FXML ImageView l16; @FXML ImageView l17; @FXML ImageView l18; @FXML ImageView l19; @FXML ImageView l20;

    ArrayList<ImageView> listaViews;
    ArrayList<String> listaLugares;
    String disciplina = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaLugares = new ArrayList<>();
        listaViews = new ArrayList<>();

        listaViews.add(l1);listaViews.add(l2);listaViews.add(l3);listaViews.add(l4);listaViews.add(l5);
        listaViews.add(l6);listaViews.add(l7);listaViews.add(l8);listaViews.add(l9);listaViews.add(l10);
        listaViews.add(l11);listaViews.add(l12);listaViews.add(l13);listaViews.add(l14);listaViews.add(l15);
        listaViews.add(l16);listaViews.add(l17);listaViews.add(l18);listaViews.add(l19);listaViews.add(l20);

        alumni = DbaseUtils.getAlumni();
        reservas= DbaseUtils.getReservas();

        //DEBUG
        System.out.println(alumni.toString());


        ArrayList<String> listaDisciplinas = DbaseUtils.getDisciplinas();
        for (String str : listaDisciplinas)
            idChooser.getItems().add(str);

         idChooser.getSelectionModel().selectedItemProperty().addListener(event -> {
             listaLugares = DbaseUtils.getLugares(idChooser.getSelectionModel().getSelectedItem());
             disciplina = idChooser.getSelectionModel().getSelectedItem();
             updateLugares(listaLugares);
             setImagesListeners(listaViews);

         });

         btn_voltar.setOnAction(event -> {
             Parent root;
             try {
                 root = FXMLLoader.load(DbaseUtils.class.getResource("views/menu.fxml"));
                 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.setTitle("Booking Masters");
                 stage.setScene(new Scene(root,600,400));
                 stage.show();
             } catch (IOException exception) {
                 exception.printStackTrace();
             }
         });

    }
    private void setImagesListeners(ArrayList<ImageView> listaViews) {
        for(int i = 0;i<20;i++){
            int finalI = i;
            listaViews.get(i).setOnMouseClicked(mouseEvent -> {
                if(!listaLugares.get(finalI).equals("0") || buscaRes()){ alertaErro(); } else{ alertaConfimacao(finalI); }
            });
        }
    }
    private void alertaConfimacao(int pos) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirme");
        alert.setContentText("Tem a certeza de que deseja reservar lugar?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isEmpty() || result.get() == ButtonType.OK) {
            listaLugares.set(pos, alumni.getId());
            DbaseUtils.reservaLugar(disciplina,listaLugares);
            Reserva novaRes = new Reserva(alumni.getNameUser(),disciplina,java.time.LocalDate.now().toString(),"1");
            reservas.add(novaRes);
            DbaseUtils.saveToFileReserva(novaRes);
            updateLugares(listaLugares);
        }
    }

    private void alertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Reserva inválida");
        alert.setContentText("Lugar já se encontra ocupado ou utilizador já possui reserva");
        alert.show();
    }

    private boolean buscaRes(){
        return listaLugares.contains(alumni.getId());
    }
    private void updateLugares(ArrayList<String> listaLugares) {
        for (int i = 0; i < listaLugares.size();i++){
            if(listaLugares.get(i).equals("0")){
                Path imageFile = Paths.get("src/resources/online-icon.png");
                try {
                    listaViews.get(i).setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }else if(listaLugares.get(i).equals(alumni.getId())){
                Path imageFile = Paths.get("src/resources/me.png");
                try {
                    listaViews.get(i).setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else{
                Path imageFile = Paths.get("src/resources/online-red-icon.png");
                try {
                    listaViews.get(i).setImage(new Image(imageFile.toUri().toURL().toExternalForm()));

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}