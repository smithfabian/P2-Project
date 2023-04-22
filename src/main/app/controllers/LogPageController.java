package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.app.views.AdminView;

import java.io.IOException;

public class LogPageController {
    Stage stage;
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
    private TableColumn<?, ?> logKategori;

    @FXML
    private TableView<?> logTable;

    @FXML
    private ToolBar toolBar;

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
}
