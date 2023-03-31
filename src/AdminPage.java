import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
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
        StackPane rootPane = new StackPane();

        Pane logOutPane = new Pane();

        HBox center = new HBox(10);
        center.setPadding(new Insets(10));
        center.setAlignment(Pos.CENTER);

        VBox buttonPane = new VBox(10);
        buttonPane.setSpacing(50);
        buttonPane.setPadding(new Insets(50));
        buttonPane.setAlignment(Pos.CENTER);

        Button b1 = new Button("Sales Data");
        b1.setPrefSize(400, 50);

        Button b2 = new Button("See log");
        b2.setPrefSize(400, 50);

        Button b3 = new Button("Add/del user");
        b3.setPrefSize(400, 50);

        Button b4 = new Button("Chance password");
        b4.setPrefSize(400, 50);

        Button logOut = new Button("Log out");
        logOut.setPrefSize(100, 50);
        logOut.setLayoutX(0);
        logOut.setLayoutY(0);

        buttonPane.getChildren().addAll(b1, b2, b3, b4);
        logOutPane.getChildren().add(logOut);
        center.getChildren().add(buttonPane);
        rootPane.getChildren().addAll(logOutPane, buttonPane);

        //root.setLeft(logOutPane);
        root.setLeft(rootPane);


        // right pane
        VBox diagramPane = new VBox(10);
        //diagramPane.setStyle("-fx-background-color: blue;");
        BarChart<String, Number> barChart1 = createBarChart();
        BarChart<String, Number> barChart2 = createBarChart();
        diagramPane.getChildren().addAll(barChart1, barChart2);
        root.setRight(diagramPane);

        // button Actions
        setButtonAction(b1, () -> SalesPage.createSalesScene(stage));
        setButtonAction(b2, () -> LogsPage.createLogsScene(stage));
        setButtonAction(b3, () -> UsersPage.createUsersScene(stage));
        setButtonAction(b4, () -> {
            Stage passwordStage = new Stage();
            PasswordPage.createPasswordScene(passwordStage);
            passwordStage.show();
        });

        // set new scene
        Scene AdminScene = new Scene(root, 1000, 500);
        stage.setScene(AdminScene);
    }

}
