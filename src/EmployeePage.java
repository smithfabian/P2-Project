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
import p2.SalesPage.SalesPage;

public class EmployeePage {
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
        button.setOnAction(actionEvent -> action.run());
    }

    public static void createEmployeesScene(Stage stage) {
        BorderPane root = new BorderPane();

        // left pane
        VBox buttonPane = new VBox(10);
        Button salesButton = new Button("Sales");
        Button passwordButton = new Button("Change password");
        Button logoutButton = new Button("Logout");
        buttonPane.getChildren().addAll(salesButton,passwordButton, logoutButton);
        buttonPane.setAlignment(Pos.CENTER);
        root.setCenter(buttonPane);

        // right pane
        VBox diagramPane = new VBox(10);
        BarChart<String, Number> barChart1 = createBarChart();
        BarChart<String, Number> barChart2 = createBarChart();
        diagramPane.getChildren().addAll(barChart1, barChart2);
        root.setRight(diagramPane);

        // button Actions
        setButtonAction(salesButton, () -> SalesPage.createSalesScene(stage));
        setButtonAction(passwordButton, () -> PasswordPage.createPasswordScene(stage));
        setButtonAction(logoutButton, () -> AdminPage.LoginPage.createLoginPage(stage));

        // set new scene
        Scene AdminScene = new Scene(root, 1000, 500);
        stage.setScene(AdminScene);
    }
}
