package p2.SalesPage;

import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SalesPage {
    public static void createSalesScene(Stage stage) {
        BorderPane rootPane = new BorderPane();
        ToggleGroup toggleGroup = new ToggleGroup();
        ToggleButton customersButton = new ToggleButton("Customers");
        ToggleButton ordersButton = new ToggleButton("Orders");
        ToggleButton itemsButton = new ToggleButton("Items");
        itemsButton.setToggleGroup(toggleGroup);
        customersButton.setToggleGroup(toggleGroup);
        ordersButton.setToggleGroup(toggleGroup);
        itemsButton.setMaxSize(150,40);
        ordersButton.setMaxSize(150,40);
        customersButton.setMaxSize(150,40);
        HBox buttonPane = new HBox(3);
        buttonPane.getChildren().addAll(customersButton,ordersButton,itemsButton);
        rootPane.setLeft(buttonPane);
        Scene salesScene = new Scene(rootPane,1000,500);
        stage.setScene(salesScene);
    }
}
