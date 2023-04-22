package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.OrderPageController;
import main.app.models.SalesModel;

import java.io.IOException;

public class OrderPageView extends Application {
    SalesModel.OrderRow row;

    public OrderPageView(SalesModel.OrderRow row) {
        this.row = row;
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(OrderPageView.class.getResource("/main/resources/orderPage.fxml"));
        Parent root = loader.load();
        OrderPageController controller = loader.getController();
        controller.setStage(stage);
        controller.setModelValues(row);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

