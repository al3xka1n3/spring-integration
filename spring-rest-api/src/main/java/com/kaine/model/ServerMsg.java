package com.kaine.model;

import lombok.Getter;

public class ServerMsg {

    @Getter
    private String welcomeMsg = "Welcome To Spring Integration Http Outbound Example!";
    @Getter
    private String currentTime;

    public ServerMsg(String currentTime){
        this.currentTime = currentTime;
    }

    public String toString(){
        return String.format("ServerMsg with welcomeMsg = %s, currentTime = %s", welcomeMsg, currentTime);
    }

}
