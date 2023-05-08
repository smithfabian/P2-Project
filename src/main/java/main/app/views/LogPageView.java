package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.LogPageController;
import main.app.models.Session;

import java.io.IOException;

public class LogPageView extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(LogPageView.class.getResource("/LogPage-view.fxml"));
        Parent root = loader.load();
        LogPageController controller = loader.getController();
        controller.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(500);
        Session.showStage("LogPage", stage);
    }
}
