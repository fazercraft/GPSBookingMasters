package tpgps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.util.Objects;


// 1 controlador e uma view para cada menu (CASO DE USO)

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/LogIn.fxml")));


        primaryStage.setTitle("BookingMasters");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
