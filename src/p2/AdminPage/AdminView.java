package p2.AdminPage;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminView extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(AdminView.class.getResource("/p2/AdminPage/adminPage.fxml"));
        Parent root = loader.load();
        AdminController controller = loader.getController();
        controller.updateBarCharts();

        Scene scene = new Scene(root);
        stage.setTitle("Sales data management system");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}