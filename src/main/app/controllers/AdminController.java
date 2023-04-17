package main.app.controllers;

import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.app.models.AdminModel;
import main.app.models.Session;
import main.app.views.AddDelUsersView;
import main.app.views.LoginView;
import main.app.views.PasswordView;
import main.app.views.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminController {
    private AdminModel model;
    private Stage stage;
    private Stage passwordStage;
    private boolean isPasswordWindowOpen;

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Button salesButton;
    @FXML
    private Button logButton;
    @FXML
    private Button userButton;
    @FXML
    private Button passwordButton;
    @FXML
    private BarChart<String, Number> barChart1;
    @FXML
    private BarChart<String, Number> barChart2;

    List<AdminModel.BarChartData> chartData1 = null;
    List<AdminModel.BarChartData> chartData2 = null;

    public AdminController(){
        this.model = new AdminModel();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setIsPasswordWindowOpen(boolean value){
        this.isPasswordWindowOpen = value;
    }

    public void salesButtonClicked(javafx.event.ActionEvent actionEvent) {
        SalesView view = new SalesView();
        try {
            view.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logButtonClicked(ActionEvent actionEvent) {
        LogPageView view = new LogPageView();
        try {
            view.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void userButtonClicked(ActionEvent actionEvent) {
        AddDelUsersView view = new AddDelUsersView();
        try {
            view.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void passwordButtonClicked(ActionEvent actionEvent) {
        if (!isPasswordWindowOpen) {
            PasswordView passwordview = new PasswordView(this);
            try {
                isPasswordWindowOpen = true;
                this.passwordStage = new Stage();
                passwordview.start(passwordStage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.passwordStage.toFront();
        }
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        Session.reset();
        LoginView view = new LoginView();
        view.start(stage);
    }

    public void updateBarCharts() {
        try {
            chartData1 = model.getChartData("someIdentifier1");
            chartData2 = model.getChartData("someIdentifier2");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Clear existing data
        barChart1.getData().clear();
        barChart2.getData().clear();

        // Create new series
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();

        // Add data to series
        for (AdminModel.BarChartData data : chartData1) {
            series1.getData().add(new XYChart.Data<>(data.getXValue(), data.getYValue()));
        }
        for (AdminModel.BarChartData data : chartData2) {
            series2.getData().add(new XYChart.Data<>(data.getXValue(), data.getYValue()));
        }

        // Add series to bar charts
        barChart1.getData().add(series1);
        barChart2.getData().add(series2);
    }
}
