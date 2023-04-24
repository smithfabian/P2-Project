package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.app.models.SalesModel;
import main.app.models.Session;
import main.app.views.*;

import java.io.IOException;
import java.sql.Date;

public class SalesPageController {
    private int customerPageNo = 1;
    private int itemPageNo = 1;
    private int orderPageNo = 1;
    private String customerMaxPageNo;
    private String itemMaxPageNo;
    private String orderMaxPageNo;

    private SalesModel salesModel;
    private Stage stage;
    @FXML
    TextField pageNumber;
    @FXML
    TextField maxPages;
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
    private TableColumn<SalesModel.OrderRow, Integer> oPostalCode;
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
    @FXML
    HBox toolbarHbox;
    @FXML
    BorderPane borderPane;
    @FXML
    TextField searchText;
    @FXML
    Button searchButton;
    @FXML
    ComboBox<String> limitDropdownMenu;

    public SalesPageController() {
        this.salesModel = new SalesModel();
    }
    @FXML
    public void initialize() {
        setLimitDropdownMenu(new String[]{"25", "50", "100"});
        setCustomerTable();
        setOrderTable();
        setItemTable();
        setupButtons();
    }

    private void setItemTable() {
        double[] columnPercentages = {0.19, 0.19, 0.19, 0.19, 0.19};

        iItemID.setCellValueFactory(new PropertyValueFactory<>("ItemID"));
        iMainGroup.setCellValueFactory(new PropertyValueFactory<>("MainGroup"));
        iSubGroup.setCellValueFactory(new PropertyValueFactory<>("SubGroup"));
        iTotalBought.setCellValueFactory(new PropertyValueFactory<>("TotalBought"));
        iTotalReturned.setCellValueFactory(new PropertyValueFactory<>("TotalReturned"));

        iItemID.prefWidthProperty().bind(itemTable.widthProperty().multiply(columnPercentages[0]));
        iMainGroup.prefWidthProperty().bind(itemTable.widthProperty().multiply(columnPercentages[1]));
        iSubGroup.prefWidthProperty().bind(itemTable.widthProperty().multiply(columnPercentages[2]));
        iTotalBought.prefWidthProperty().bind(itemTable.widthProperty().multiply(columnPercentages[3]));
        iTotalReturned.prefWidthProperty().bind(itemTable.widthProperty().multiply(columnPercentages[4]));

        itemTable.setItems(salesModel.getItemTable());
    }

    private void setOrderTable() {
        double[] columnPercentages = {0.16, 0.16, 0.16, 0.16, 0.16, 0.16};

        oOrderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        oDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        oQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        oCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        oPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        oCity.setCellValueFactory(new PropertyValueFactory<>("City"));

        oOrderID.prefWidthProperty().bind(orderTable.widthProperty().multiply(columnPercentages[0]));
        oDate.prefWidthProperty().bind(orderTable.widthProperty().multiply(columnPercentages[1]));
        oQuantity.prefWidthProperty().bind(orderTable.widthProperty().multiply(columnPercentages[2]));
        oCustomerID.prefWidthProperty().bind(orderTable.widthProperty().multiply(columnPercentages[3]));
        oPostalCode.prefWidthProperty().bind(orderTable.widthProperty().multiply(columnPercentages[4]));
        oCity.prefWidthProperty().bind(orderTable.widthProperty().multiply(columnPercentages[5]));

        orderTable.setItems(salesModel.getOrderTable());
    }

    private void setupButtons() {
        toggleGroup = new ToggleGroup();
        customersButton.setToggleGroup(toggleGroup);
        ordersButton.setToggleGroup(toggleGroup);
        itemsButton.setToggleGroup(toggleGroup);
        customersButton.fire();
        toolbarHbox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.98));
    }

    private void setCustomerTable() {
        double[] columnPercentages = {0.19, 0.19, 0.19, 0.19, 0.19};

        cIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        cMainSectorColumn.setCellValueFactory(new PropertyValueFactory<>("MainSector"));
        cSubSectorColumn.setCellValueFactory(new PropertyValueFactory<>("SubSector"));
        cBoughtColumn.setCellValueFactory(new PropertyValueFactory<>("TotalBought"));
        cReturnColumn.setCellValueFactory(new PropertyValueFactory<>("TotalReturned"));

        cIDColumn.prefWidthProperty().bind(customerTable.widthProperty().multiply(columnPercentages[0]));
        cMainSectorColumn.prefWidthProperty().bind(customerTable.widthProperty().multiply(columnPercentages[1]));
        cSubSectorColumn.prefWidthProperty().bind(customerTable.widthProperty().multiply(columnPercentages[2]));
        cBoughtColumn.prefWidthProperty().bind(customerTable.widthProperty().multiply(columnPercentages[3]));
        cReturnColumn.prefWidthProperty().bind(customerTable.widthProperty().multiply(columnPercentages[4]));

        customerTable.setItems(salesModel.getCustomerTable());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void itemTableFront() {
        itemTable.toFront();
        pageNumber.setText("" + itemPageNo);
        if (itemMaxPageNo == null){
            setItemMaxPageNo();
        }
        maxPages.setText(itemMaxPageNo);
    }

    public void orderTableFront() {
        orderTable.toFront();
        pageNumber.setText("" + orderPageNo);
        if (orderMaxPageNo == null){
            setOrderMaxPageNo();
        }
        maxPages.setText(orderMaxPageNo);
    }

    public void customerTableFront() {
        customerTable.toFront();
        pageNumber.setText("" + customerPageNo);
        if (customerMaxPageNo == null){
            setCustomerMaxPageNo();
        }
        maxPages.setText(customerMaxPageNo);
    }

    public void setLimitDropdownMenu(String[] list){
        for (String item:list){
            limitDropdownMenu.getItems().add(item);
            limitDropdownMenu.setValue(list[0]);
        }
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

    public void nextPageButtonClicked() {
        ToggleButton buttonToggled = (ToggleButton) toggleGroup.getSelectedToggle();
        if (buttonToggled.equals(customersButton)) {
            if (customerPageNo + 1 <= Integer.parseInt(maxPages.getText())) {
                customerPageNo += 1;
                salesModel.createCustomerTable(customerPageNo - 1, limitDropdownMenu.getValue(), searchText.getText());
                pageNumber.setText("" + customerPageNo);
                setCustomerTable();
            }
        }
        else if (buttonToggled.equals(itemsButton)) {
            if (itemPageNo + 1 <= Integer.parseInt(maxPages.getText())) {
                itemPageNo += 1;
                salesModel.createItemTable(itemPageNo - 1, limitDropdownMenu.getValue(), searchText.getText());
                pageNumber.setText("" + itemPageNo);
                setItemTable();
            }
        }
        else if (buttonToggled.equals(ordersButton)) {
            if (orderPageNo + 1 <= Integer.parseInt(maxPages.getText())) {
                orderPageNo += 1;
                salesModel.createOrderTable(orderPageNo - 1, limitDropdownMenu.getValue(), searchText.getText());
                pageNumber.setText("" + orderPageNo);
                setOrderTable();
            }
        }

    }

    public void prevPageButtonClicked() {
        ToggleButton buttonToggled = (ToggleButton) toggleGroup.getSelectedToggle();
        if (buttonToggled.equals(customersButton)) {
            if (customerPageNo - 1 > 0){
                customerPageNo -= 1;
                salesModel.createCustomerTable(customerPageNo - 1, limitDropdownMenu.getValue(), searchText.getText());
                pageNumber.setText("" + customerPageNo);
                setCustomerTable();
            }
        }
        else if (buttonToggled.equals(itemsButton)) {
            if (itemPageNo - 1 > 0) {
                itemPageNo -= 1;
                salesModel.createItemTable(itemPageNo - 1, limitDropdownMenu.getValue(), searchText.getText());
                pageNumber.setText("" + itemPageNo);
                setItemTable();
            }
        }
        else if (buttonToggled.equals(ordersButton)) {
            if (orderPageNo - 1 > 0) {
                orderPageNo -= 1;
                salesModel.createOrderTable(orderPageNo - 1, limitDropdownMenu.getValue(), searchText.getText());
                pageNumber.setText("" + orderPageNo);
                setOrderTable();
            }
        }
    }

    public void searchEnterKey(KeyEvent event){
        if (event.getCode().toString().equals("ENTER")) {
            searchButtonClicked();
        }
    }

    public void searchButtonClicked(){
        ToggleButton buttonToggled = (ToggleButton) toggleGroup.getSelectedToggle();
        if (buttonToggled.equals(customersButton)) {
            maxPages.setText("" + salesModel.getMaxPagesCustomerTable(searchText.getText(), limitDropdownMenu.getValue()));
            customerPageNo = 1;
            salesModel.createCustomerTable(customerPageNo - 1, limitDropdownMenu.getValue(), searchText.getText());
            pageNumber.setText("" + customerPageNo);
            setCustomerTable();
        }
        else if (buttonToggled.equals(itemsButton)) {
            maxPages.setText("" + salesModel.getMaxPagesItemTable(searchText.getText(), limitDropdownMenu.getValue()));
            itemPageNo = 1;
            salesModel.createItemTable(itemPageNo - 1, limitDropdownMenu.getValue(), searchText.getText());
            pageNumber.setText("" + itemPageNo);
            setItemTable();
        }
        else if (buttonToggled.equals(ordersButton)) {
            maxPages.setText("" + salesModel.getMaxPagesOrderTable(searchText.getText(), limitDropdownMenu.getValue()));
            orderPageNo = 1;
            salesModel.createOrderTable(orderPageNo - 1, limitDropdownMenu.getValue(), searchText.getText());
            pageNumber.setText("" + orderPageNo);
            setOrderTable();
        }
    }

    public void itemRowClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            SalesModel.ItemRow row = (SalesModel.ItemRow) itemTable.getSelectionModel().getSelectedItem();
            ItemPageView view = new ItemPageView(row);
            try {
                view.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void customerRowClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            SalesModel.CustomerRow row = (SalesModel.CustomerRow) customerTable.getSelectionModel().getSelectedItem();
            CustomerPageView view = new CustomerPageView(row.getID());
            try {
                view.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void orderRowClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            SalesModel.OrderRow row = (SalesModel.OrderRow) orderTable.getSelectionModel().getSelectedItem();
            OrderPageView view = new OrderPageView(row);
            try {
                view.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void changedLimit(){
        searchButtonClicked();
    }

    public void changedOffset(){
        ToggleButton buttonToggled = (ToggleButton) toggleGroup.getSelectedToggle();
        if (buttonToggled.equals(customersButton)) {
            customerPageNo = Integer.parseInt(pageNumber.getText());;
            salesModel.createCustomerTable(customerPageNo - 1, limitDropdownMenu.getValue(), searchText.getText());
            pageNumber.setText("" + customerPageNo);
            setCustomerTable();
        }
        else if (buttonToggled.equals(itemsButton)) {
            itemPageNo = Integer.parseInt(pageNumber.getText());;
            salesModel.createItemTable(itemPageNo - 1, limitDropdownMenu.getValue(), searchText.getText());
            pageNumber.setText("" + itemPageNo);
            setItemTable();
        }
        else if (buttonToggled.equals(ordersButton)) {
            orderPageNo = Integer.parseInt(pageNumber.getText());;
            salesModel.createOrderTable(orderPageNo - 1, limitDropdownMenu.getValue(), searchText.getText());
            pageNumber.setText("" + orderPageNo);
            setOrderTable();
        }
    }

    public void pageNoEnterKey(KeyEvent event){
        if (event.getCode().toString().equals("ENTER")) {
            if (pageNumber.getText().isBlank() || Integer.parseInt(pageNumber.getText()) < 1){
                pageNumber.setText("1");
            }
            if (Integer.parseInt(pageNumber.getText()) > Integer.parseInt(maxPages.getText())){
                pageNumber.setText(maxPages.getText());
            }
            changedOffset();
        }
    }

    public void setCustomerMaxPageNo(){
        this.customerMaxPageNo = salesModel.getMaxPagesCustomerTable(searchText.getText(), limitDropdownMenu.getValue());
    }
    public void setItemMaxPageNo(){
        this.itemMaxPageNo = salesModel.getMaxPagesItemTable(searchText.getText(), limitDropdownMenu.getValue());
    }
    public void setOrderMaxPageNo(){
        this.orderMaxPageNo = salesModel.getMaxPagesOrderTable(searchText.getText(), limitDropdownMenu.getValue());
    }
}
