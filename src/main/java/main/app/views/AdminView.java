package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.AdminController;

import java.io.IOException;

public class AdminView extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(AdminView.class.getResource("/adminPage.fxml"));
        Parent root = loader.load();

        AdminController controller = loader.getController();
        controller.setStage(stage);
        controller.updateBarCharts();

        Scene scene = new Scene(root);
        stage.setTitle("Sales data management system");
        stage.setMinWidth(1000);
        stage.setMinHeight(500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}