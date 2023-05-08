package main.app.controllers;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.app.models.AddDelModel;
import main.app.models.LogModel;
import main.app.views.AdminView;

import java.io.IOException;

public class LogPageController {
    LogModel logModel = new LogModel();
    Stage stage;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button back;

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField searchBar;

    @FXML
    private TableColumn<?, ?> logDate;

    @FXML
    private TableColumn<?, ?> logDetails;

    @FXML
    private TableColumn<?, ?> logCategory;
    @FXML
    private TableColumn<?,?> logClassName;

    @FXML
    private TableView<LogModel.LogRow> logTable;

    @FXML
    private ToolBar toolBar;
    @FXML
    public void initialize() {
        setTable();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void backButtonPressed() {
        AdminView view = new AdminView();
        try {
            view.start(stage);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setTable() {
        logModel.fillTable();
        logDate.setCellValueFactory(new PropertyValueFactory<>("LogDate"));
        logClassName.setCellValueFactory(new PropertyValueFactory<>("ClassName"));
        logCategory.setCellValueFactory(new PropertyValueFactory<>("LogLevel"));
        logDetails.setCellValueFactory(new PropertyValueFactory<>("LogMessage"));

        logTable.setItems(logModel.getTable());
    }

    public void searchTable() {
        String searchTerm = searchBar.getText();

        String logDate = searchTerm;
        String className = searchTerm;
        String logLevel = searchTerm;
        String logMessage = searchTerm;

        logModel.fillTable(logDate, className, logLevel, logMessage);
        logTable.setItems(logModel.getTable());
    }
}
