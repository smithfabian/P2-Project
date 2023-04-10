package main.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.app.models.SalesModel;
import main.app.views.AddNewCustomerView;
import main.app.views.AddNewItemView;
import main.app.views.AddNewOrderView;

import java.io.IOException;

public class SalesPageController {

    private SalesModel salesModel;
    private Stage stage;
    @FXML
    StackPane stackPane;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private ToggleButton customersButton;
    @FXML
    private ToggleButton ordersButton;
    @FXML
    private ToggleButton itemsButton;
    @FXML
    private Button addButton;
    @FXML
    private TableView customerTable;
    @FXML
    private TableView orderTable;
    @FXML
    private TableView itemTable;
    @FXML
    private TableColumn<SalesModel.CustomerRow,String> IDColumn;
    @FXML
    private TableColumn<SalesModel.CustomerRow,String> mSectorColumn;
    @FXML
    private TableColumn<SalesModel.CustomerRow,String> sSectorColumn;
    @FXML
    private TableColumn<SalesModel.CustomerRow,String> postalColumn;
    @FXML
    private TableColumn<SalesModel.CustomerRow,Integer> boughtColumn;
    @FXML
    private TableColumn<SalesModel.CustomerRow,Integer> returnColumn;

    public SalesPageController() {
        this.salesModel = new SalesModel();
    }
    @FXML
    public void initialize() {
        customersButton.fire();
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        mSectorColumn.setCellValueFactory(new PropertyValueFactory<>("MainSector"));
        sSectorColumn.setCellValueFactory(new PropertyValueFactory<>("SubSector"));
        postalColumn.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        boughtColumn.setCellValueFactory(new PropertyValueFactory<>("TotalBought"));
        returnColumn.setCellValueFactory(new PropertyValueFactory<>("TotalReturned"));
        customerTable.setItems(salesModel.getCustomerTable());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void itemTableFront() {
        itemTable.toFront();
    }

    public void orderTableFront(ActionEvent actionEvent) {
        orderTable.toFront();
    }

    public void customerTableFront(ActionEvent actionEvent) {
        customerTable.toFront();
    }

    public void addButtonClicked(ActionEvent actionEvent) {
        ToggleButton buttonToggled = (ToggleButton) toggleGroup.getSelectedToggle();
        if (buttonToggled.equals(customersButton)) {
            AddNewCustomerView view = new AddNewCustomerView();
            try {
                view.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (buttonToggled.equals(itemsButton)) {
            AddNewItemView view = new AddNewItemView();
            try {
                view.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else if (buttonToggled.equals(ordersButton)) {
            AddNewOrderView view = new AddNewOrderView();
            try {
                view.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void backButtonClicked(ActionEvent actionEvent) {
    }
}
