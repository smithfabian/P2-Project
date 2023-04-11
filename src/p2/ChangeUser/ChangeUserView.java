package p2.ChangeUser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

//DOES NOT WORK
public class ChangeUserView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChangeUserView.class.getResource("ChangeUser-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Change user");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }


    }
