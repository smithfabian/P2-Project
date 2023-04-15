package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.AdminController;
import main.app.controllers.PasswordController;
import main.app.models.PasswordModel;

import java.io.IOException;

public class PasswordView extends Application {
    private AdminController adminController;

    public PasswordView(AdminController adminController){
        this.adminController = adminController;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(AdminView.class.getResource("/main/resources/passwordPage.fxml"));
        Parent root = loader.load();
        PasswordController controller = loader.getController();
        controller.setStage(stage);
        controller.setAdminController(adminController);
        controller.setModel(new PasswordModel());

        Scene scene = new Scene(root);
        stage.setTitle("Change Password");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
