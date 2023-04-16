package main.app.models;

public class Session {
    private static Integer loggedInUser;
    private static boolean isAdmin = false;

    public static void setLoggedInUser(Integer userId){
        loggedInUser = userId;
    }

    public static Integer getLoggedInUser(){
        return loggedInUser;
    }

    public static void setIsAdmin(boolean value){
        isAdmin = value;
    }

    public static boolean getIsAdmin(){
        return isAdmin;
    }

    public static void reset(){
        loggedInUser = null;
        isAdmin = false;
    }
}