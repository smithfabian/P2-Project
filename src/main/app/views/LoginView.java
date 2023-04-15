package main.app.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.LoginController;
import main.app.models.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class LoginView {

    public void start (Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/Login-view.fxml"));
            Parent root = loader.load();

            LoginController controller = loader.getController();
            controller.setStage(stage);

            Scene scene = new Scene(root);
            stage.setTitle("Sales data management system");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}