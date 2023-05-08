package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.NewItemController;
import main.app.models.Session;

import java.io.IOException;

public class AddNewItemView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(AddNewItemView.class.getResource("/AddNewItem-view.fxml"));
        Parent root = loader.load();
        NewItemController controller = loader.getController();
        controller.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        Session.showStage("AddNewItem", stage);
    }
}
