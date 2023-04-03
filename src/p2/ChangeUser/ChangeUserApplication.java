package p2.ChangeUser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import p2.AddDelUser.AddDelApplication;

public class ChangeUserApplication extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(AddDelApplication.class.getResource("ChangeUser-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 500);
            stage.setTitle("Add or delete user ");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
    }

