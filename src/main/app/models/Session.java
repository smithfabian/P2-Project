package main.app.models;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class Session {
    private static Integer loggedInUser;
    private static boolean isAdmin = false;
    private static Map<String, Stage> openStages = new HashMap<>();

    public static void setLoggedInUser(Integer userId) {
        loggedInUser = userId;
    }

    public static Integer getLoggedInUser() {
        return loggedInUser;
    }

    public static void setIsAdmin(boolean value) {
        isAdmin = value;
    }

    public static boolean getIsAdmin() {
        return isAdmin;
    }

    public static void logout() {
        loggedInUser = null;
        isAdmin = false;

        Map<String, Stage> copyOfOpenStages = new HashMap<>(openStages);
        copyOfOpenStages.forEach((identifier, stage) -> stage.close());
        openStages.clear();
    }

    public static void showStage(String identifier, Stage newStage) {
        Stage existingStage = openStages.get(identifier);

        if (existingStage == null) {
            openStages.put(identifier, newStage);
            newStage.setOnHidden(e -> openStages.remove(identifier));
            newStage.show();
        } else {
            existingStage.toFront();
        }
    }
}