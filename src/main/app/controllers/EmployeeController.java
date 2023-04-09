package main.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.app.models.EmployeeModel;
import main.app.views.LoginView;
import main.app.views.SalesView;

import java.io.IOException;

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
    LineChart<NumberAxis,NumberAxis> chartUpper;
    @FXML
    LineChart<NumberAxis,NumberAxis> chartLower;

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
        XYChart.Series upperSeries = new XYChart.Series();
        XYChart.Series lowerSeries = new XYChart.Series();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X Axis");
        yAxis.setLabel("Y Axis");
        for (int i = 0; i < employeeModel.getBarChartLowerX().size() ; i++) {
            upperSeries.getData().add(new XYChart.Data<>(employeeModel.getBarChartUpperX().get(i), employeeModel.getBarChartUpperY().get(i)));
            lowerSeries.getData().add(new XYChart.Data<>(employeeModel.getBarChartLowerX().get(i), employeeModel.getBarChartLowerY().get(i)));

        }
        chartUpper.getData().add(upperSeries);
        chartLower.getData().add(lowerSeries);

    }

    public void switchToSalesScene(ActionEvent actionEvent) {
        SalesView view = new SalesView();
        try {
            view.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToChangePassWordScene(ActionEvent actionEvent) {
        //TODO
    }

    public void SwitchToLoginPageScene(ActionEvent actionEvent) throws IOException {
        LoginView view = new LoginView();
        view.start(stage);
    }
}
