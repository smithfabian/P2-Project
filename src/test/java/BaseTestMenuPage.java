import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.app.models.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

@ExtendWith(ApplicationExtension.class)
public class BaseTestMenuPage {

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

    void logic_for_test_should_have_n_buttons_and_2_graphs(FxRobot robot, Integer noButtons) throws TimeoutException {
        Set<Button> buttons = robot.lookup(".button").queryAll();
        BarChart barChart1 = robot.lookup("#barChart1").queryAs(BarChart.class);
        BarChart barChart2 = robot.lookup("#barChart2").queryAs(BarChart.class);

        Assertions.assertThat(buttons.size()).isEqualTo(noButtons);
        Assertions.assertThat(barChart1).isNotNull();
        Assertions.assertThat(barChart2).isNotNull();
    }

    void logic_for_test_should_have_a_button_that_leads_to_sales_page(FxRobot robot) {
        Button salesButton = robot.lookup("#salesButton").queryButton();
        robot.clickOn(salesButton);
        AnchorPane salesPage = robot.lookup("#SalesPage").queryAs(AnchorPane.class);
        Assertions.assertThat(salesPage).isNotNull();
    }


    void logic_for_test_should_have_a_button_that_leads_to_change_password_page(FxRobot robot) {
        AtomicReference<Stage> newStage = new AtomicReference<>();

        Button changePasswordButton = robot.lookup("#passwordButton").queryButton();
        robot.clickOn(changePasswordButton);

        robot.targetWindow(newStage.get());
        AnchorPane passwordPage = robot.lookup("#passwordPage").queryAs(AnchorPane.class);
        Assertions.assertThat(passwordPage).isNotNull();
    }


    void logic_for_test_should_have_a_button_that_logout_user(FxRobot robot) {
        Button logoutButton = robot.lookup("#logoutButton").queryButton();
        robot.clickOn(logoutButton);

        Assertions.assertThat(logoutButton.getText()).isEqualTo("Logout");
        Assertions.assertThat(Session.getLoggedInUser()).isNull();
    }
}