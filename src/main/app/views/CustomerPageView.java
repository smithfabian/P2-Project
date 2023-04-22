package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.CustomerPageController;
import main.app.controllers.ItemPageController;
import main.app.models.SalesModel;

import java.io.IOException;

public class CustomerPageView extends Application {
    SalesModel.ItemRow row;

    public CustomerPageView(SalesModel.ItemRow row) {
        this.row = row;
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(CustomerPageView.class.getResource("/main/resources/ItemPage-view.fxml"));
        Parent root = loader.load();
        CustomerPageController controller = loader.getController();
        controller.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

