package p2.LoginPage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("/Login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1000,500);
        //stage.setScene(new Scene(fxmlLoader.load(),1000,500));
        stage.setTitle("Sales data management system");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}