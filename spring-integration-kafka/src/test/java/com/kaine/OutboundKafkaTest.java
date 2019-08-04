package com.kaine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.KafkaHeaders;
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
    @Qualifier("kafkaChannel.output")
    private MessageChannel kafkaChannel;

    @Test
    public void testCase() {
        System.out.println("Sending message from test...");
        Message<?> message = MessageBuilder
                .withPayload("testing message payload")
                .setHeader(KafkaHeaders.MESSAGE_KEY, "key")
                .setHeader(KafkaHeaders.TOPIC, "demo")
                .build();

        kafkaChannel.send(message);
    }
}
