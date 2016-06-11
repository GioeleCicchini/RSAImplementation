package Ui;/**
 * Created by Gioele on 11/06/2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Fxml/home.fxml"));

        primaryStage.setTitle("Chat Protetta");
        primaryStage.setScene(new Scene(root));


        primaryStage.show();
    }
}
