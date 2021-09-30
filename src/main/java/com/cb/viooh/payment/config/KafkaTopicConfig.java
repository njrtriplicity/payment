//package com.cb.viooh.payment.config;
//
//import org.apache.kafka.clients.admin.AdminClientConfig;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.KafkaAdmin;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaTopicConfig {
//
//    @Value(value = "${bootstrapServer}")
//    private String bootstrapServer;
//
//    private String orderCreateTopic;
//
//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        return new KafkaAdmin(configs);
//    }
//
//    @Bean
//    public NewTopic orderCreateTopic() {
//        return new NewTopic("order.create.payment", 1, (short) 1);
//    }
//
//    public String getOrderCreateTopic() {
//        return orderCreateTopic().name();
//    }
//
//    public void setOrderCreateTopic(String orderCreateTopic) {
//        this.orderCreateTopic = orderCreateTopic;
//    }
//}
