package main.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.app.models.EmployeeModel;
import main.app.models.Session;
import main.app.views.LoginView;
import main.app.views.PasswordView;
import main.app.views.SalesView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeController {
    private Stage stage;

    private EmployeeModel employeeModel = new EmployeeModel();
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button salesButton;
    @FXML
    Button changePasswordButton;
    @FXML
    Button logoutButton;
    @FXML
    private BarChart<String, Number> barChart1;
    @FXML
    private BarChart<String, Number> barChart2;
    List<EmployeeModel.BarChartData> chartData1 = null;
    List<EmployeeModel.BarChartData> chartData2 = null;

    public EmployeeController() {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        createLineCharts();
    }
    public void createLineCharts() {
        try {
            chartData1 = employeeModel.getChartData("someIdentifier1");
            chartData2 = employeeModel.getChartData("someIdentifier2");
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        // Clear existing data
        barChart1.getData().clear();
        barChart2.getData().clear();

        // Create new series
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();

        // Add data to series
        for (EmployeeModel.BarChartData data : chartData1) {
            series1.getData().add(new XYChart.Data<>(data.getXValue(), data.getYValue()));
        }
        for (EmployeeModel.BarChartData data : chartData2) {
            series2.getData().add(new XYChart.Data<>(data.getXValue(), data.getYValue()));
        }

        // Add series to bar charts
        barChart1.getData().add(series1);
        barChart1.getYAxis().setLabel("Units sold");
        barChart2.getData().add(series2);

    }

    public void switchToSalesScene(ActionEvent actionEvent) {
        SalesView view = new SalesView();
        try {
            view.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void SwitchToLoginPageScene(ActionEvent actionEvent) throws IOException {
        Session.logout();
        LoginView view = new LoginView();
        view.start(stage);
    }

    public void passwordButtonClicked() {
        PasswordView passwordview = new PasswordView();
        try {
            passwordview.start(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
