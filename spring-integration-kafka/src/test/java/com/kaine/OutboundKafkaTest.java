package com.kaine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = IntegrationConfig.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OutboundKafkaTest {

    @Autowired
    MessageChannel kafkaChannel;

    @Test
    public void testCase() {
        System.out.println("________Sending message from test________");
        Message<String> message = MessageBuilder
                .withPayload("testing message payload")
                .build();

        kafkaChannel.send(message);
    }
}
