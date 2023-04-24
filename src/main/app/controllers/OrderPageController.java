package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.app.models.CustomerPageModel;
import main.app.models.OrderPageModel;
import main.app.models.SalesModel;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

public class OrderPageController {
    Stage stage;
    OrderPageModel model;
    @FXML
    Button backButton;
    @FXML
    Button applyButton;
    @FXML
    Button okButton;
    @FXML
    Label pageHeadline;
    @FXML
    TextField orderId;
    @FXML
    DatePicker date;
    @FXML
    TextField totQuantity;
    @FXML
    TextField customerID;
    @FXML
    TextField postalCode;
    @FXML
    TextField city;

    @FXML
    TableView<OrderPageModel.orderItemRow> table;
    @FXML
    TableColumn<CustomerPageModel.customerPageRow, Date> orderLineQtyColumn;
    @FXML
    TableColumn<CustomerPageModel.customerPageRow, Date> itemIdColumn;
    @FXML
    TableColumn<CustomerPageModel.customerPageRow, Date> itemMainGroupColumn;
    @FXML
    TableColumn<CustomerPageModel.customerPageRow, Date> itemSubGroupColumn;

    @FXML
    BorderPane borderPane;
    @FXML
    HBox toolbarHbox;

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setModelValues(SalesModel.OrderRow row) {
        model = new OrderPageModel(row);
        setLabelTexts();

        orderLineQtyColumn.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemMainGroupColumn.setCellValueFactory(new PropertyValueFactory<>("itemMainGroup"));
        itemSubGroupColumn.setCellValueFactory(new PropertyValueFactory<>("itemSubGroup"));
        table.setItems(model.getTable());
    }

    public void setLabelTexts() {
        toolbarHbox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.98));
        orderId.setText(String.valueOf(model.getOrderID()));
        pageHeadline.setText("Order " + model.getOrderID());
        date.setValue(model.getDate().toLocalDate());
        totQuantity.setText(String.valueOf(model.getQuantity()));
        customerID.setText(model.getCustomerId());
        postalCode.setText(String.valueOf(model.getPostalCode()));
        city.setText(model.getCity());
    }

    public void backButtonClicked() {
        stage.close();
    }

    public void applyButtonClicked() {
        // String InvoiceDate, String AccountNum, String City, String PostalCode
        model.updateOrder(java.sql.Date.valueOf(date.getValue()), customerID.getText(), city.getText(), Integer.parseInt(postalCode.getText()));
    }

    public void okButtonClicked() {
        model.updateOrder(java.sql.Date.valueOf(date.getValue()), customerID.getText(), city.getText(), Integer.parseInt(postalCode.getText()));
        stage.close();
    }

    public void cancelOrderButtonClicked() {
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm order cancelation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure yiu want to delete order " + model.getOrderID());
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)){
            model.deleteOrder();
            stage.close();
        }
    }
}
