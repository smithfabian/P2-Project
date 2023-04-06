package main.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;

public class SalesPageController {

    @FXML
    ToggleButton customersButton;
    @FXML
    ToggleButton ordersButton;
    @FXML
    ToggleButton itemsButton;
    @FXML
    Button addButton;
    @FXML
    TableView customerTable;
    @FXML
    TableView orderTable;
    @FXML
    TableView itemTable;

    public SalesPageController() {

    }







    public FXMLLoader getView() {
        return new FXMLLoader(SalesPageController.class.getResource("/main/resources/Employeepage-view.fxml"));
    }


}
