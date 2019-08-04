package com.kaine;

import com.kaine.msg.ServerMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = IntegrationConfig.class)
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
}