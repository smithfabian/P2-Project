package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.app.models.ItemPageModel;
import main.app.models.SalesModel;

public class ItemPageController {
    Stage stage;
    ItemPageModel itemPageModel;
    @FXML
    Label itemID;
    @FXML
    Label mainGroup;
    @FXML
    Label subGroup;
    @FXML
    Label totalBought;
    @FXML
    Label totalReturned;


    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setModelValues(SalesModel.ItemRow row) {
        itemPageModel = new ItemPageModel(row);
        setLabelText();

    }

    public void setLabelText() {
        itemID.setText("Item ID: " + itemPageModel.getItemID());
        mainGroup.setText("Item main group: " + itemPageModel.getMainGroup());
        subGroup.setText("Item sub group: " + itemPageModel.getSubGroup());
        totalBought.setText("Total bought: " + itemPageModel.getTotalBought());
        totalReturned.setText("Total returned: " + itemPageModel.getTotalReturned());
    }
}
