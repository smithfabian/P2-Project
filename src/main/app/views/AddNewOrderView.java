package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.NewOrderController;
import main.app.models.Session;

import java.io.IOException;

public class AddNewOrderView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(AddNewOrderView.class.getResource("/main/resources/AddNewOrder-view.fxml"));
        Parent root = loader.load();
        NewOrderController controller = loader.getController();
        controller.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        Session.showStage("AddNewOrder", stage);
    }
}
