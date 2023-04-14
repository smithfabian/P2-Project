package main.app.models;

public class Session {
    private Integer loggedInUser;

    public void setLoggedInUser(Integer userId){
        this.loggedInUser = userId;
    }

    public Integer getLoggedInUser(){
        return this.loggedInUser;
    }
}
