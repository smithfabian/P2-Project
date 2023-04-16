package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LogPageController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button back;

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableColumn<?, ?> logDate;

    @FXML
    private TableColumn<?, ?> logDetaljer;

    @FXML
    private TableColumn<?, ?> logKatagori;

    @FXML
    private TableView<?> logTable;

    @FXML
    private ToolBar toolBar;

    public void setStage(Stage stage) {
    }
}
