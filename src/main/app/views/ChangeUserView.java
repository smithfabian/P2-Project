package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.ChangeUserController;

import java.io.IOException;
import java.util.Objects;

//DOES NOT WORK
public class ChangeUserView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(ChangeUserView.class.getResource("/main/resources/ChangeUser-view.fxml"));
        Parent root = loader.load();

        ChangeUserController controller = loader.getController();
        controller.setStage(stage);

        Scene scene = new Scene(root);
        stage.setMinWidth(350);
        stage.setMinHeight(150);
        stage.setTitle("Change user");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }


    }
