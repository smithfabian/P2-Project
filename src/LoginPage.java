
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.application.*;
import javafx.scene.control.Button;

public class LoginPage extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        VBox loginFields = new VBox(5);
        Label title = new Label("Sales Data Management System");
        title.setFont(Font.font("verdana", FontWeight.BOLD,20));
        TextField usernameInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        usernameInput.setMaxWidth(200);
        passwordInput.setMaxWidth(200);
        Label loginAttempt = new Label("");
        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                if (usernameInput.getText().equals("admin") && passwordInput.getText().equals("admin")) {
                    loginAttempt.setText("Login successful");
                    loginAttempt.setTextFill(Color.GREEN);
                    createAdminScene(stage);
                }
                else {
                    loginAttempt.setText("Log in unsuccessful");
                    loginAttempt.setTextFill(Color.RED);
                }
            }
        });

        loginButton.setMaxSize(100,100);
        loginFields.getChildren().addAll(title,usernameInput,passwordInput,loginButton,loginAttempt);
        loginFields.setAlignment(Pos.CENTER);
        Scene scene = new Scene(loginFields,1000,500);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    /* utility method that creates a bar chart */
    private BarChart<String, Number> createBarChart(){
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
    private void setButtonAction(Button button, Runnable action){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                action.run();
            }
        });
    }


    private void createAdminScene(Stage stage) {
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

    private void createSalesScene(Stage stage) {
        // TODO implement sales scene
    }
    private void createLogsScene(Stage stage) {
        // TODO implement logs scene
    }
    private void createUsersScene(Stage stage) {
        // TODO implement user scene
    }
    private void createPasswordScene(Stage stage) {
        // TODO implement password scene
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
