package com.kaine;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.messaging.MessageChannel;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Bean
    public Map<String,Object> producerConfigs() {
        Map<String,Object> props = new HashMap<>();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("client.id", "client-id-1");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String,Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String,Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public MessageChannel kafkaChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow producer(KafkaTemplate<String,Object> kafkaTemplate, MessageChannel kafkaChannel) {
        System.out.println("________Starting producer flow________");

        return f -> f
                .channel(kafkaChannel)
                .handle(Kafka
                        .outboundChannelAdapter(kafkaTemplate)
                        .topic("spring-integration-kafka-topic"));
    }
}
