package main.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.app.models.ItemPageModel;
import main.app.models.SalesModel;
import main.app.models.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ItemPageController {
    private static final Logger logger = LogManager.getLogger(ItemPageController.class.getName());
    Stage stage;
    ItemPageModel itemPageModel;
    @FXML
    Label pageHeadline;
    @FXML
    TextField mainGroup;
    @FXML
    TextField subGroup;
    @FXML
    TextField totalBought;
    @FXML
    TextField totalReturned;
    @FXML
    LineChart<String,Integer> boughtChart;
    @FXML
    LineChart<String, Integer> returnedChart;
    @FXML
    BorderPane borderPane;
    @FXML
    HBox toolbarHbox;


    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setModelValues(SalesModel.ItemRow row) {
        itemPageModel = new ItemPageModel(row);
        setLabelText();
        fillCharts();
    }

    public void setLabelText() {
        toolbarHbox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.98));
        pageHeadline.setText("Item id: " + itemPageModel.getItemID());
        mainGroup.setText(itemPageModel.getMainGroup());
        subGroup.setText(itemPageModel.getSubGroup());
        totalBought.setText("" + itemPageModel.getTotalBought());
        totalReturned.setText("" + itemPageModel.getTotalReturned());
    }
    public void fillCharts() {
        List<Integer> boughtAxis = itemPageModel.getBoughtAxis();
        List<Integer> returnedAxis = itemPageModel.getReturnedAxis();
        List<String> dateAxis = itemPageModel.getDateAxis();
        // Clear existing data
        boughtChart.getData().clear();
        boughtChart.getData().clear();

        // Create new series
        XYChart.Series<String, Integer> boughtSeries = new XYChart.Series<>();
        XYChart.Series<String, Integer> returnedSeries = new XYChart.Series<>();

        // Add data to series
        for (int i = 0; i < boughtAxis.size() ; i++) {
            boughtSeries.getData().add(new XYChart.Data<>(dateAxis.get(i),boughtAxis.get((i))));
            returnedSeries.getData().add(new XYChart.Data<>(dateAxis.get(i),returnedAxis.get((i))));

        }

        // Add series to bar charts
        boughtChart.getData().add(boughtSeries);
        boughtChart.getYAxis().setLabel("Units sold");
        returnedChart.getData().add(returnedSeries);
        returnedChart.getYAxis().setLabel("Units returned");
    }

    public void backButtonClicked() {
        stage.close();
    }

    public void applyButtonClicked(){
        if (!mainGroup.getText().equals(itemPageModel.getMainGroup()) || !subGroup.getText().equals(itemPageModel.getSubGroup())){
            itemPageModel.updateItem(mainGroup.getText(), subGroup.getText());
        }
    };

    public void okButtonClicked(){
        if (!mainGroup.getText().equals(itemPageModel.getMainGroup()) || !subGroup.getText().equals(itemPageModel.getSubGroup())){
            itemPageModel.updateItem(mainGroup.getText(), subGroup.getText());
        }
        stage.close();
    };

    public void deleteButtonClicked(){
        if (Session.getLoggedInUser() == 30) {
            logger.info("Usability test item deleted clicked");
        }
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm deleting item");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete item " + itemPageModel.getItemID() + "?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)){
            itemPageModel.deleteItem();
            stage.close();
        }
    };
}
