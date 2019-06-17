package com.kaine.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.kaine.model.ServerMsg;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @RequestMapping("/getServerTime")
    public ServerMsg getServerTime(){

        System.out.println("# GET Request!");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        ServerMsg serverTime = new ServerMsg(dtf.format(now));
        return serverTime;
    }
}