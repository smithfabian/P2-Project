import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.app.controllers.AdminController;
import main.app.views.AdminView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@ExtendWith(ApplicationExtension.class)
public class TestAdminPage extends BaseTestMenuPage {

    @Start
    private void start(Stage stage) throws IOException {
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

    // Testing user story P2-38: https://cct-p2-group5.atlassian.net/browse/P2-38

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
    void should_have_a_button_that_leads_to_change_password_page(FxRobot robot){
        logic_for_test_should_have_a_button_that_leads_to_change_password_page(robot);
    }

    @Test
    void should_have_a_button_that_logout_user(FxRobot robot){
        logic_for_test_should_have_a_button_that_logout_user(robot);
    }

    @Test
    void should_have_a_button_that_leads_to_sales_page(FxRobot robot){
        logic_for_test_should_have_a_button_that_leads_to_sales_page(robot);
    }

    @Test
    void should_have_5_buttons_and_2_graphs(FxRobot robot) throws TimeoutException {
        logic_for_test_should_have_n_buttons_and_2_graphs(robot, 5);
    }
}