package tpgps.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tpgps.DbaseUtils;
import tpgps.model.User;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ReserveSeatController implements Initializable {

    User alumni;

    @FXML
    ChoiceBox<String> idChooser;
    @FXML ImageView l1; @FXML ImageView l2; @FXML ImageView l3; @FXML ImageView l4; @FXML ImageView l5;
    @FXML ImageView l6; @FXML ImageView l7; @FXML ImageView l8; @FXML ImageView l9; @FXML ImageView l10;
    @FXML ImageView l11; @FXML ImageView l12; @FXML ImageView l13; @FXML ImageView l14; @FXML ImageView l15;
    @FXML ImageView l16; @FXML ImageView l17; @FXML ImageView l18; @FXML ImageView l19; @FXML ImageView l20;

    ArrayList<ImageView> listaViews;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaViews = new ArrayList<>();
        listaViews.add(l1);listaViews.add(l2);listaViews.add(l3);listaViews.add(l4);listaViews.add(l5);
        listaViews.add(l6);listaViews.add(l7);listaViews.add(l8);listaViews.add(l9);listaViews.add(l10);
        listaViews.add(l11);listaViews.add(l12);listaViews.add(l13);listaViews.add(l14);listaViews.add(l15);
        listaViews.add(l16);listaViews.add(l17);listaViews.add(l18);listaViews.add(l19);listaViews.add(l20);

        alumni = DbaseUtils.getAlumni();
        ArrayList<String> listaDisciplinas = DbaseUtils.getDisciplinas();

        for (String str : listaDisciplinas)
            idChooser.getItems().add(str);

         idChooser.getSelectionModel().selectedItemProperty().addListener(event -> {
             ArrayList<Integer> listaLugares = DbaseUtils.getLugares(idChooser.getSelectionModel().getSelectedItem());
             updateLugares(listaLugares);

         });

    }


    private void updateLugares(ArrayList<Integer> listaLugares) {
        for (int i = 0; i < listaLugares.size();i++){
            if(listaLugares.get(i) == 0){
                Path imageFile = Paths.get("/src/resources/online-icon.png");
                try {
                    listaViews.get(i).setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            if(listaLugares.get(i) == 1){
                Path imageFile = Paths.get("/src/resources/online-red-icon.png");
                try {
                    listaViews.get(i).setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}