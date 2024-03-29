package com.kaine.endpoint;

import com.kaine.msg.HelloMsg;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Log
public class WelcomeEndpoint {

    public Message<?> get(Message<String> msg) {
        String name = msg.getPayload();
        // Log
        log.info("Request with name = " + name);

        // Get currentTime
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentTime = dtf.format(now);

        String strMsg = "Hello " + name + "! " + "Welcome to Spring Integration with Spring Boot!";

        HelloMsg returnMsg = new HelloMsg(strMsg, currentTime);

        return MessageBuilder.withPayload(returnMsg)
                .copyHeadersIfAbsent(msg.getHeaders())
                .setHeader("http_statusCode", HttpStatus.OK)
                .build();
    }
}
