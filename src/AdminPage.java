import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminPage {

    /* utility method that creates a bar chart */
    private static BarChart<String, Number> createBarChart(){
        CategoryAxis xAxis1 = new CategoryAxis();
        NumberAxis yAxis1 = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis1, yAxis1);
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.getData().add(new XYChart.Data<>("A", 50));
        dataSeries1.getData().add(new XYChart.Data<>("B", 75));
        barChart.getData().add(dataSeries1);
        return barChart;
    }

    /* utility method that sets button action */
    private static void setButtonAction(Button button, Runnable action){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                action.run();
            }
        });
    }

    public static void createAdminScene(Stage stage) {
        BorderPane root = new BorderPane();

        // left pane
        VBox buttonPane = new VBox(10);
        Button salesButton = new Button("Sales");
        Button logsButton = new Button("Logs");
        Button usersButton = new Button("Users");
        Button passwordButton = new Button("Change password");
        Button logoutButton = new Button("Logout");
        buttonPane.getChildren().addAll(salesButton,logsButton,usersButton,passwordButton, logoutButton);
        buttonPane.setAlignment(Pos.CENTER);
        root.setLeft(buttonPane);

        // right pane
        VBox diagramPane = new VBox(10);
        BarChart<String, Number> barChart1 = createBarChart();
        BarChart<String, Number> barChart2 = createBarChart();
        diagramPane.getChildren().addAll(barChart1, barChart2);
        root.setRight(diagramPane);

        // button Actions
        setButtonAction(salesButton, () -> createSalesScene(stage));
        setButtonAction(logsButton, () -> createLogsScene(stage));
        setButtonAction(usersButton, () -> createUsersScene(stage));
        setButtonAction(passwordButton, () -> createPasswordScene(stage));

        // set new scene
        Scene AdminScene = new Scene(root, 1000, 500);
        stage.setScene(AdminScene);
    }

    private static void createSalesScene(Stage stage) {
        // TODO implement sales scene
    }
    private static void createLogsScene(Stage stage) {
        // TODO implement logs scene
    }
    private static void createUsersScene(Stage stage) {
        // TODO implement user scene
    }
    private static void createPasswordScene(Stage stage) {
        // TODO implement password scene
    }

}
