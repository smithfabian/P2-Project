package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.AddDelController;
import main.app.controllers.NewCustomerController;

import java.io.IOException;

public class AddDelUsersView extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(AddDelUsersView.class.getResource("/main/resources/AddDel-view.fxml"));
        Parent root = loader.load();
        AddDelController controller = loader.getController();
        controller.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
