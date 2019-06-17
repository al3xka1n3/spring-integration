package com.kaine;

import com.kaine.msg.ServerMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:http-outbound.xml")
@SpringBootTest
public class HttpOutboundGatewayTest {

    @Autowired
    @Qualifier("reply.channel")
    PollableChannel receivedChannel;

    @Autowired
    @Qualifier("get.request.channel")
    MessageChannel getRequestChannel;

    @Test
    public void testCase() {
        Message<?> message = MessageBuilder.withPayload("").build();
        getRequestChannel.send(message);
        Message<?> receivedMsg = receivedChannel.receive();
        ServerMsg serverMsg = (ServerMsg) receivedMsg.getPayload();
        System.out.println("############## ServerMsg ##############");
        System.out.println(serverMsg.toString());
        System.out.println("############## Done! ##############");
    }
}