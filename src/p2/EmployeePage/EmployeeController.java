package p2.EmployeePage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import p2.LoginPage.LoginController;

import java.io.IOException;

public class EmployeeController {
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

    public EmployeeController(Stage stage) throws IOException {

        stage.setScene(new Scene(getView().load(),1000,500));

    }
    public EmployeeController() {
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

    public FXMLLoader getView() {
        return new FXMLLoader(EmployeeController.class.getResource("/p2/EmployeePage/EmployeePage-view.fxml"));
    }

    public void switchToSalesScene(ActionEvent actionEvent) {
        // TO DO
    }

    public void switchToChangePassWordScene(ActionEvent actionEvent) {
        //TO DO
    }

    public void SwitchToLoginPageScene(ActionEvent actionEvent) throws IOException {
        new p2.LoginPage.LoginController((Stage) anchorPane.getScene().getWindow());
    }
}
