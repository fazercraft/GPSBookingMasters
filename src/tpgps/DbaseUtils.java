package tpgps;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import tpgps.model.User;
import java.io.IOException;


// esta class vai servir de apoio ao login
// e tratar a ligacao à BD
// BD vai estar representada num ficheiro de texto


public class DbaseUtils {

    public static User aluno = null;
    public static  User getAlumni(){ return aluno; }

    public static void logInUser(ActionEvent event, String mail, String passAl) {
        System.out.println("login pressed");

        // ler ficheiro de texto com users e pass
        aluno  = findUser(mail);

        if( aluno == null){
            System.out.println("user not found in database");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falha no Login");
            alert.setContentText("User not found");
            alert.show();
        }else{
            if(!findPass(aluno, passAl)){
                System.out.println("pass did not match");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Password incorrect");
                alert.show();
            }else{
                // salta para o MENU Scene
                try {
                    Parent root = FXMLLoader.load(DbaseUtils.class.getResource("views/menu.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Booking Masters");
                    stage.setScene(new Scene(root,600,400));
                    stage.show();

                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    private static Boolean findPass(User alumni,String pwd) {
        return alumni.getPwdUser().equals(pwd);
    }

    private static User findUser(String pwd) {
        // TODO
        // procurar e devolver aluno
        // caso encontre devolve o new aluno
        // caso contrario null

        // quem fizer isto terá de criar um ficheiro de texto parecido com isto:

        // Andre Oliveira
        // 2013011067
        // password

        // Daniel Rocket League
        // 2013010000
        // password

        // por ai fora

        return null;
    }


}
