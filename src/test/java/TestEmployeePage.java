import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.app.controllers.EmployeeController;
import main.app.models.Session;
import main.app.views.AdminView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;


import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

@ExtendWith(ApplicationExtension.class)
public class TestEmployeePage {
    private Stage stage;

    @Start
    private void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(AdminView.class.getResource("/Employeepage-view.fxml"));
        Parent root = loader.load();
        EmployeeController controller = loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setTitle("Sales data management system");
        stage.setMinWidth(1000);
        stage.setMinHeight(500);
        stage.setScene(scene);
        stage.show();
    }

    // Testing user Story P2-39 https://cct-p2-group5.atlassian.net/browse/P2-39

    @BeforeAll
    static void setupBeforeTests(){
        Session.setIsAdmin(false);
        Session.setLoggedInUser(2);
    }

    @Test
    void should_have_3_buttons_and_2_graphs(FxRobot robot) throws TimeoutException {
        Set<Button> buttons = robot.lookup(".button").queryAll();
        BarChart barChart1 = robot.lookup("#barChart1").queryAs(BarChart.class);
        BarChart barChart2 = robot.lookup("#barChart2").queryAs(BarChart.class);

        Assertions.assertThat(buttons.size()).isEqualTo(3);
        Assertions.assertThat(barChart1).isNotNull();
        Assertions.assertThat(barChart2).isNotNull();
    }

    @Test
    void should_have_a_button_that_leads_to_sales_page(FxRobot robot) {
        Button salesButton = robot.lookup("#salesButton").queryButton();
        robot.clickOn(salesButton);
        AnchorPane salesPage = robot.lookup("#SalesPage").queryAs(AnchorPane.class);
        Assertions.assertThat(salesPage).isNotNull();
    }

    @Test
    void should_have_a_button_that_leads_to_change_password(FxRobot robot) {
        Button changePasswordButton = robot.lookup("#changePasswordButton").queryButton();
        robot.clickOn(changePasswordButton);
        AnchorPane salesPage = robot.lookup("#passwordPage").queryAs(AnchorPane.class);
        Assertions.assertThat(salesPage).isNotNull();
    }

    @Test
    void should_have_a_button_that_logout_user(FxRobot robot) {
        Button logoutButton = robot.lookup("#logoutButton").queryButton();
        robot.clickOn(logoutButton);

        Assertions.assertThat(logoutButton.getText()).isEqualTo("Logout");
        Assertions.assertThat(Session.getLoggedInUser()).isNull();
    }
}
