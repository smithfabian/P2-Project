import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import main.app.App;
import main.app.controllers.AdminController;
import main.app.models.Session;
import main.app.views.AdminView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

@ExtendWith(ApplicationExtension.class)
public class TestAdminPage {
    Stage stage;
    private FxRobot robot;

    @Start
    private void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(AdminView.class.getResource("/adminPage.fxml"));
        Parent root = loader.load();

        AdminController controller = loader.getController();
        controller.setStage(stage);
        controller.updateBarCharts();

        Scene scene = new Scene(root);
        stage.setTitle("Sales data management system");
        stage.setMinWidth(1000);
        stage.setMinHeight(500);
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    void setupBeforeTests() {
        Session.setIsAdmin(true);
        Session.setLoggedInUser(1);
    }

    @AfterEach
    public void tearDown() throws Exception {
        // makes sure robot does not count buttons in the new password stage
        FxToolkit.cleanupStages();
    }

    @Test
    void should_have_5_buttons_and_2_graphs(FxRobot robot) throws TimeoutException {
        Set<Button> buttons = robot.lookup(".button").queryAll();
        BarChart barChart1 = robot.lookup("#barChart1").queryAs(BarChart.class);
        BarChart barChart2 = robot.lookup("#barChart2").queryAs(BarChart.class);

        Assertions.assertThat(buttons.size()).isEqualTo(5);
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
    void should_have_a_button_that_leads_to_logs_page(FxRobot robot) {
        Button salesButton = robot.lookup("#logButton").queryButton();
        robot.clickOn(salesButton);
        AnchorPane salesPage = robot.lookup("#logPage").queryAs(AnchorPane.class);
        Assertions.assertThat(salesPage).isNotNull();
    }

    @Test
    void should_have_a_button_that_leads_to_users_page(FxRobot robot) {
        Button salesButton = robot.lookup("#userButton").queryButton();
        robot.clickOn(salesButton);
        AnchorPane salesPage = robot.lookup("#usersPage").queryAs(AnchorPane.class);
        Assertions.assertThat(salesPage).isNotNull();
    }


    @Test
    void should_have_a_button_that_leads_to_change_password_page(FxRobot robot) {
        AtomicReference<Stage> newStage = new AtomicReference<>();

        Button changePasswordButton = robot.lookup("#passwordButton").queryButton();
        robot.clickOn(changePasswordButton);

        robot.targetWindow(newStage.get());
        AnchorPane passwordPage = robot.lookup("#passwordPage").queryAs(AnchorPane.class);
        Assertions.assertThat(passwordPage).isNotNull();
    }


    @Test
    void should_have_a_button_that_logout_user(FxRobot robot) {
        Button logoutButton = robot.lookup("#logoutButton").queryButton();
        robot.clickOn(logoutButton);

        Assertions.assertThat(logoutButton.getText()).isEqualTo("Logout");
        Assertions.assertThat(Session.getLoggedInUser()).isNull();
    }
}