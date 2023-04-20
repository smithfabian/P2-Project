package main.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.app.models.SalesModel;
import main.app.models.Session;
import main.app.views.*;

import java.io.IOException;
import java.sql.Date;

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
    private TableView customerTable;
    @FXML
    private TableView orderTable;
    @FXML
    private TableView itemTable;
    @FXML
    private TableColumn<SalesModel.CustomerRow,String> cIDColumn;
    @FXML
    private TableColumn<SalesModel.CustomerRow,String> cMainSectorColumn;
    @FXML
    private TableColumn<SalesModel.CustomerRow,String> cSubSectorColumn;
    @FXML
    private TableColumn<SalesModel.CustomerRow,Integer> cBoughtColumn;
    @FXML
    private TableColumn<SalesModel.CustomerRow,Integer> cReturnColumn;
    @FXML
    private TableColumn<SalesModel.OrderRow,Integer>  oOrderID;
    @FXML
    private TableColumn<SalesModel.OrderRow, Date> oDate;
    @FXML
    private TableColumn<SalesModel.OrderRow, Integer> oQuantity;
    @FXML
    private TableColumn<SalesModel.OrderRow, String> oCustomerID;
    @FXML
    private TableColumn<SalesModel.OrderRow, String> oPostalCode;
    @FXML
    private TableColumn<SalesModel.OrderRow, String> oCity;
    @FXML
    private TableColumn<SalesModel.ItemRow,String> iItemID;
    @FXML
    private TableColumn<SalesModel.ItemRow,String> iMainGroup;
    @FXML
    private TableColumn<SalesModel.ItemRow,String> iSubGroup;
    @FXML
    private TableColumn<SalesModel.ItemRow,Integer> iTotalBought;
    @FXML
    private TableColumn<SalesModel.ItemRow, Integer> iTotalReturned;

    public SalesPageController() {
        this.salesModel = new SalesModel();
    }
    @FXML
    public void initialize() {
        setCustomerTable();
        setOrderTable();
        setItemTable();
        setupButtons();
    }

    private void setItemTable() {
        iItemID.setCellValueFactory(new PropertyValueFactory<>("ItemID"));
        iMainGroup.setCellValueFactory(new PropertyValueFactory<>("MainGroup"));
        iSubGroup.setCellValueFactory(new PropertyValueFactory<>("SubGroup"));
        iTotalBought.setCellValueFactory(new PropertyValueFactory<>("TotalBought"));
        iTotalReturned.setCellValueFactory(new PropertyValueFactory<>("TotalReturned"));
        itemTable.setItems(salesModel.getItemTable());
    }

    private void setOrderTable() {
        oOrderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        oDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        oQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        oCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        oPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        oCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        orderTable.setItems(salesModel.getOrderTable());
    }

    private void setupButtons() {
        toggleGroup = new ToggleGroup();
        customersButton.setToggleGroup(toggleGroup);
        ordersButton.setToggleGroup(toggleGroup);
        itemsButton.setToggleGroup(toggleGroup);
        customersButton.fire();
    }

    private void setCustomerTable() {
        cIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        cMainSectorColumn.setCellValueFactory(new PropertyValueFactory<>("MainSector"));
        cSubSectorColumn.setCellValueFactory(new PropertyValueFactory<>("SubSector"));
        cBoughtColumn.setCellValueFactory(new PropertyValueFactory<>("TotalBought"));
        cReturnColumn.setCellValueFactory(new PropertyValueFactory<>("TotalReturned"));
        customerTable.setItems(salesModel.getCustomerTable());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void itemTableFront() {
        itemTable.toFront();
    }

    public void orderTableFront() {
        orderTable.toFront();
    }

    public void customerTableFront() {
        customerTable.toFront();
    }

    public void addButtonClicked() {
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

    public void backButtonClicked() {
        if (Session.getIsAdmin()) {
            AdminView view = new AdminView();
            try {
                view.start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            EmployeeView view = new EmployeeView();
            try {
                view.start(stage);
            } catch (IOException ignored) {
            }
        }
    }
}
