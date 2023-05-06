package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.ItemPageController;
import main.app.models.SalesModel;
import main.app.models.Session;

import java.io.IOException;

public class ItemPageView extends Application {
    SalesModel.ItemRow row;

    public ItemPageView(SalesModel.ItemRow row) {
        this.row = row;
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(ItemPageView.class.getResource("/main/resources/ItemPage-view.fxml"));
        Parent root = loader.load();
        ItemPageController controller = loader.getController();
        controller.setStage(stage);
        controller.setModelValues(row);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        Session.showStage("ItemPage" + row.getItemID(), stage);
    }
}

