package com.kaine.model;

public class ServerMsg {

    private String welcomeMsg = "Welcome To Spring Integration Http Outbound Example!";
    private String currentTime;

    public ServerMsg(){
    }

    public ServerMsg(String currentTime){
        this.setCurrentTime(currentTime);
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String toString(){
        String info = String.format("ServerMsg with welcomeMsg = %s, currentTime = %s", welcomeMsg, currentTime);
        return info;
    }

}
