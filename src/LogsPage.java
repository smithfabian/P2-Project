import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LogsPage {
    public static void createLogsScene(Stage stage) {
        BorderPane root = new BorderPane();

        // Top pane
        HBox searchAndButtonsPane = new HBox(10);
        TextField searchBox = new TextField();
        Button searchButton = new Button("Search");
        Button backButton = new Button("Back");

        searchButton.setOnAction(actionEvent -> {
            // TODO add search logic
        });

        backButton.setOnAction(actionEvent -> {
            AdminPage.createAdminScene(stage);
        });

        searchAndButtonsPane.getChildren().addAll(searchBox, searchButton, backButton);
        searchAndButtonsPane.setAlignment(Pos.CENTER);
        searchAndButtonsPane.setPadding(new Insets(10));

        // log table
        TableView<LogEntry> logTable = new TableView<>();
        TableColumn<LogEntry, String> dateColumn = new TableColumn<>("Date");
        TableColumn<LogEntry, String> messageColumn = new TableColumn<>("Message");

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        logTable.getColumns().addAll(dateColumn, messageColumn);

        // load data
        ObservableList<LogEntry> logData = FXCollections.observableArrayList(
                // TODO add logic here to update table with actual log data
                new LogEntry("2023-03-30 10:00", "Message 1"),
                new LogEntry("2023-03-30 10:05", "Message 2")
        );
        logTable.setItems(logData);

        // layout
        VBox layoutPane = new VBox(10, searchAndButtonsPane, logTable);
        layoutPane.setPadding(new Insets(10));

        root.setCenter(layoutPane);

        Scene logsScene = new Scene(root, 1000, 500);
        stage.setScene(logsScene);
    }

    /* This is a model (schema) for logTable table,
     * should be in its own package or class with other models */
    public static class LogEntry {
        private String date;
        private String message;

        public LogEntry(String date, String message) {
            this.date = date;
            this.message = message;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}

