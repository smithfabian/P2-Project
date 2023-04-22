package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.CustomerPageController;

import java.io.IOException;

public class CustomerPageView extends Application {
    String row;

    public CustomerPageView(String row) {
        this.row = row;
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(CustomerPageView.class.getResource("/main/resources/customerPage-view.fxml"));
        Parent root = loader.load();
        CustomerPageController controller = loader.getController();
        controller.setStage(stage);
        controller.setModelValues(row);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

