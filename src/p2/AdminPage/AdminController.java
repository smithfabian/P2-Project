package p2.AdminPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import p2.PasswordPage.PasswordView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class AdminController {
    AdminModel model;

    @FXML
    private AnchorPane anchorPane;
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

    public void salesButtonClicked(javafx.event.ActionEvent actionEvent) {
    }

    public void logButtonClicked(ActionEvent actionEvent) {
    }


    public void userButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/p2/AddDelUser/AddDel-view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add or delete users");
        stage.setScene(scene);
        stage.show();
    }

    public void passwordButtonClicked(ActionEvent actionEvent) {
        PasswordView passwordView = new PasswordView();
        try {
            passwordView.start(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
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
