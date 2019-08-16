package com.kaine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Configuration
@EnableIntegration
@Component
@ComponentScan
public class IntegrationConfig {

    @Bean
    public MessageChannel kafkaChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow producer(KafkaTemplate<?,?> kafkaTemplate, MessageChannel kafkaChannel) {
        System.out.println("________Starting producer flow________");

        return f -> f
                .channel(kafkaChannel)
                .handle(Kafka
                        .outboundChannelAdapter(kafkaTemplate)
                        .topic("spring-integration-kafka-topic"));
    }
}
