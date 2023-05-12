package main.app.controllers;

import javafx.scene.chart.LineChart;
import javafx.stage.Stage;
import main.app.models.MainPageModel;
import main.app.models.Session;
import main.app.views.AddDelUsersView;
import main.app.views.LoginView;
import main.app.views.PasswordView;
import main.app.views.*;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainPageController {
    private MainPageModel model;
    private Stage stage;
    private static final Logger logger = LogManager.getLogger(MainPageController.class.getName());

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
    private LineChart<String, Number> lineChart;

    List<MainPageModel.ChartData> chartData1 = null;
    List<MainPageModel.ChartData> chartData2 = null;

    public MainPageController(){
        this.model = new MainPageModel();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        updateBarCharts();
        checkUser();
    }

    private void checkUser() {
        if (!Session.getIsAdmin()) {
            logButton.setVisible(false);
            userButton.setVisible(false);
        }
    }

    public void salesButtonClicked() {
        if (Session.getLoggedInUser() == 30) {
            logger.info("Usability test click on sales button");
        }
        SalesView view = new SalesView();
        try {
            view.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logButtonClicked() {
        if (Session.getLoggedInUser() == 30) {
            logger.info("Usability test click on log button");
        }
        LogPageView view = new LogPageView();
        try {
            view.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void userButtonClicked() {
        if (Session.getLoggedInUser() == 30) {
            logger.info("Usability test click on user button");
        }
        AddDelUsersView view = new AddDelUsersView();
        try {
            view.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void passwordButtonClicked() {
        if (Session.getLoggedInUser() == 30) {
            logger.info("Usability test click on change password button");
        }
        PasswordView passwordview = new PasswordView();
        try {
            passwordview.start(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logoutButtonClicked() {
        Session.logout();
        LoginView view = new LoginView();
        view.start(stage);
    }

    public void updateBarCharts() {
        try {
            chartData1 = model.getBarChartData();
            chartData2 = model.getLineChartData();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        // Clear existing data
        barChart1.getData().clear();
        lineChart.getData().clear();

        // Create new series
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();

        // Add data to series
        for (MainPageModel.ChartData data : chartData1) {
            series1.getData().add(new XYChart.Data<>(data.getXValue(), data.getYValue()));
        }
        for (MainPageModel.ChartData data : chartData2) {
            series2.getData().add(new XYChart.Data<>(data.getXValue(), data.getYValue()));
        }

        // Add series to bar charts
        barChart1.getData().add(series1);
        barChart1.getYAxis().setLabel("Units sold");
        lineChart.getYAxis().setLabel("Units sold");
        lineChart.getData().add(series2);
    }
}
