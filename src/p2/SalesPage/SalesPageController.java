package p2.SalesPage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import p2.LoginPage.LoginController;

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
        return new FXMLLoader(SalesPageController.class.getResource("/p2/EmployeePage/EmployeePage-view.fxml"));
    }


}
