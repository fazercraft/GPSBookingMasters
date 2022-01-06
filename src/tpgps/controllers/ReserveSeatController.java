package tpgps.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tpgps.DbaseUtils;
import tpgps.model.User;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ReserveSeatController implements Initializable {

    User alumni;

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
        ArrayList<String> listaDisciplinas = DbaseUtils.getDisciplinas();

        //DEBUG
        System.out.println(alumni.toString());


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
        listaViews.get(0).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(0).equals("1") || buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(0,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(1).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(1).equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(1,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(2).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(2).equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(2,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(3).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(3).equals("1") || buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(3,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(4).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(4).equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(4,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(5).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(5).equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(5,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(6).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(6).equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(6,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(7).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(7).equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(7,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(8).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(8).equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(8,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(9).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(9).equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(9,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(10).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(10) .equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(10,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(11).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(11).equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(11,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(12).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(12) .equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(12,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(13).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(13).equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(13,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(14).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(14) .equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(14,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(15).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(15) .equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(15,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(16).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(16) .equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(16,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(17).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(17) .equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(17,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(18).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(18) .equals("1")|| buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(18,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
        listaViews.get(19).setOnMouseClicked(mouseEvent -> {
            if(listaLugares.get(19).equals("1") || buscaRes(disciplina)){
                System.out.println("Lugar já ocupado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("lugar inválido");
                alert.setContentText("Lugar já se encontra ocupado");
                alert.show();
            }else{
                System.out.println("confirmação");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme");
                alert.setContentText("Tem a certeza de que deseja reservar lugar?");
                alert.show();

                listaLugares.set(19,"1");
                alumni.addReserva(disciplina);
                DbaseUtils.reservaLugar(disciplina,listaLugares);
                updateLugares(listaLugares);

            }
        });
    }

    private boolean buscaRes(String disciplina) {
        return alumni.getListaReservas().contains(disciplina);
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
            }
            if(listaLugares.get(i).equals("1")){
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