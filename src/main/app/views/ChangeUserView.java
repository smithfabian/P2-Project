package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.AddDelController;
import main.app.controllers.ChangeUserController;

import java.io.IOException;

public class ChangeUserView extends Application {
    AddDelController addDelController;
    public ChangeUserView(AddDelController addDelController) {
        this.addDelController = addDelController;
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(ChangeUserView.class.getResource("/main/resources/ChangeUser-view.fxml"));
        Parent root = loader.load();

        ChangeUserController controller = loader.getController();
        controller.setAddDelController(this.addDelController);
        controller.setStage(stage);

        Scene scene = new Scene(root);
        stage.setMinWidth(350);
        stage.setMinHeight(150);
        stage.setTitle("Change user");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }


    }
