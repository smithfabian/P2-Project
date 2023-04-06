package p2.PasswordPage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import p2.AdminPage.AdminController;
import p2.AdminPage.AdminView;

import java.io.IOException;

public class PasswordView extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(AdminView.class.getResource("/p2/PasswordPage/passwordPage.fxml"));
        Parent root = loader.load();
        PasswordController controller = loader.getController();
        controller.setStage(stage);
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
