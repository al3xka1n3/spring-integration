package com.kaine;

import com.kaine.msg.ServerMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.http.dsl.Http;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = HttpOutboundGatewayTest.Config.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpOutboundGatewayTest {

    @Autowired
    DirectChannel requestChannel;

    @Autowired
    PollableChannel responseChannel;

    @Test
    public void testCase() {
        Message<?> message = MessageBuilder.withPayload("").build();
        requestChannel.send(message);
        Message<?> receivedMsg = responseChannel.receive();
        ServerMsg serverMsg = (ServerMsg) receivedMsg.getPayload();
        System.out.println("############## ServerMsg ##############");
        System.out.println(serverMsg.toString());
        System.out.println("############## Done! ##############");
    }

    @Configuration
    @EnableIntegration
    public static class Config {

        @Bean
        public IntegrationFlow flow() {
            return IntegrationFlows
                    .from("requestChannel")
                    .handle(Http
                            .outboundGateway("http://localhost:8080/getServerTime")
                            .httpMethod(HttpMethod.GET)
                            .expectedResponseType(ServerMsg.class))
                    .channel(MessageChannels.queue("responseChannel"))
                    .get();
        }
    }
}