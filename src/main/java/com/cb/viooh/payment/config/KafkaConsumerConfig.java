package com.cb.viooh.payment.config;

import com.cb.viooh.payment.common.Constants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${bootstrapServer}")
    private String bootstrapServer;

    public Map<String, Object> paymentConsumerConfigs() {
        Map<String, Object> property = new HashMap<>();
        property.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        property.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        property.put(ConsumerConfig.GROUP_ID_CONFIG, Constants.PAYMENT_GROUP_ID);
        property.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, Constants.KAFKA_OFFSET);
        property.put(
                ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 10);
        return property;
    }

    public ConsumerFactory<String, String> paymentConsumerFactory() {
        Map<String, Object> property = paymentConsumerConfigs();
        property.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        return new DefaultKafkaConsumerFactory<>(property);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    kafkaListenerPaymentContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(paymentConsumerFactory());
        return factory;
    }
}
