//package com.cb.viooh.payment.config;
//
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaProducerConfig {
//
//    @Value(value = "${bootstrapServer}")
//    private String bootstrapServer;
//
//    public Map<String, Object> orderProducerConfigs() {
//        Map<String, Object> property = new HashMap<>();
//        property.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        property.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        property.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return property;
//    }
//
//    @Bean
//    public ProducerFactory<String, String> orderProducerFactory() {
//        return new DefaultKafkaProducerFactory<>(orderProducerConfigs());
//    }
//
//    @Bean("orderProducerTemplate")
//    public KafkaTemplate<String, String> kafkaOrderTemplate() {
//        return new KafkaTemplate<>(orderProducerFactory());
//    }
//}
