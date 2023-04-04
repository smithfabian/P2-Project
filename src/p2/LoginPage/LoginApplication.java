package p2.LoginPage;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginController loginController = new LoginController(stage);
        stage.setTitle("Sales data management system");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}