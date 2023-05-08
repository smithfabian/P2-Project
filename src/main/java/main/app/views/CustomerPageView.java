package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.CustomerPageController;
import main.app.models.Session;

import java.io.IOException;

public class CustomerPageView extends Application {
    String customerID;

    public CustomerPageView(String customerID) {
        this.customerID = customerID;
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(CustomerPageView.class.getResource("/customerPage-view.fxml"));
        Parent root = loader.load();
        CustomerPageController controller = loader.getController();
        controller.setStage(stage);
        controller.setModelValues(customerID);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        Session.showStage("CustomerPage" + customerID, stage);
    }
}

