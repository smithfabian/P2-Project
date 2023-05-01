package main.app.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.app.models.CustomerPageModel;
import main.app.models.OrderPageModel;
import main.app.models.OrderRow;
import main.app.models.SalesModel;
import main.app.views.ItemPageView;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

public class OrderPageController {
    Stage stage;
    OrderPageModel model;
    ObservableList<OrderPageModel.orderItemRow> orderItems;
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
    TableColumn<OrderPageModel.orderItemRow, Integer> orderLineQtyColumn;
    @FXML
    TableColumn<OrderPageModel.orderItemRow, String> itemIdColumn;
    @FXML
    TableColumn<OrderPageModel.orderItemRow, String> itemMainGroupColumn;
    @FXML
    TableColumn<OrderPageModel.orderItemRow, String> itemSubGroupColumn;

    @FXML
    BorderPane borderPane;
    @FXML
    HBox toolbarHbox;

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setModelValues(OrderRow row) {
        model = new OrderPageModel(row);
        setLabelTexts();

        orderLineQtyColumn.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemMainGroupColumn.setCellValueFactory(new PropertyValueFactory<>("itemMainGroup"));
        itemSubGroupColumn.setCellValueFactory(new PropertyValueFactory<>("itemSubGroup"));
        orderItems = model.getTable();
        table.setItems(orderItems);

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

    public void addItem(){
        OrderPageModel.orderItemRow row = new OrderPageModel.orderItemRow("", 0, "", "");
        row.setItemIdDisable(false);
        row.setIsNewRow(true);
        orderItems.add(row);
    }

    public void backButtonClicked() {
        stage.close();
    }

    public void applyButtonClicked() {
        model.updateOrder(java.sql.Date.valueOf(date.getValue()), customerID.getText(), city.getText(), Integer.parseInt(postalCode.getText()));

        for(OrderPageModel.orderItemRow row : table.getItems()){
            String quantity = row.getItemQty().getText();
            String originalQty = String.valueOf(row.getOriginalQty());
            if (!quantity.equals(originalQty)){
                model.updateOrderItems(Integer.parseInt(quantity), model.getOrderID(), row.getItemId().getText());
                if(row.getIsNewRow() && !row.getItemQty().getText().equals("0")){
                    model.insertOrderItems(Integer.parseInt(quantity), row.getItemId().getText());
                }
            }
        }
        orderItems = model.getTable();
    }

    public void okButtonClicked() {
        applyButtonClicked();
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

    public void itemRowClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            OrderPageModel.orderItemRow orderItemRow = table.getSelectionModel().getSelectedItem();
            SalesModel.ItemRow SalesItemRow = orderItemRow.getSalesPageItemRow();
            ItemPageView view = new ItemPageView(SalesItemRow);
            try {
                view.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
