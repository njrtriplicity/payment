package com.cb.viooh.payment.service.impl;

import com.cb.viooh.payment.common.Constants;
import com.cb.viooh.payment.config.KafkaTopicConfig;
import com.cb.viooh.payment.dto.PaymentCreateDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class PaymentListenerService {

    @Autowired
    private Gson gson;

    @Resource(name = "orderProducerTemplate")
    private KafkaTemplate orderProducerTemplate;

    @Autowired
    private KafkaTopicConfig kafkaTopicConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentListenerService.class);

    public String sendOrderCreateRequest(
            PaymentCreateDto paymentCreateDto,
            String paymentId,
            String orderId) {
        String payload = gson.toJson(paymentCreateDto);
        Message<String> message =
                MessageBuilder.withPayload(payload)
                        .setHeader(KafkaHeaders.TOPIC, kafkaTopicConfig.getOrderCreateTopic())
                        .setHeader(Constants.PAYMENT_ID, String.valueOf(paymentId).getBytes())
                        .setHeader(
                                Constants.ORDER_ID, String.valueOf(orderId).getBytes())
                        .build();
        LOGGER.info("Sending the order create data to payment = {}", payload);

        try {
            orderProducerTemplate.send(message);
        } catch (Exception e) {
            LOGGER.error("Unable to send data to Thor");
        }
        return "SUCCESS";
    }

    @KafkaListener(
            topics = "order.create.payment",
            containerFactory = "kafkaListenerPaymentContainerFactory")
    public void receiveOrderPaymentRequest(
            @Payload String message, @Headers MessageHeaders messageHeaders) throws Exception {

        if (checkForMandatoryHeaders(messageHeaders, message) == false) {
            return;
        }

        if (!StringUtils.isEmpty(message)) {
            LOGGER.info(">>>>> Message from order create Topic: {} ", message);
            String paymentId =
                    new String((byte[]) messageHeaders.get(Constants.PAYMENT_ID));
            String orderId =
                    new String((byte[]) messageHeaders.get(Constants.ORDER_ID));

            PaymentCreateDto paymentCreateDto =
                    gson.fromJson(message, PaymentCreateDto.class);

            LOGGER.info(
                    "Order payment request to be processed  = {}  ",
                    stringfyJson(paymentCreateDto));
        }
    }

    public static String stringfyJson(Object object) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String jsonValue = null;

        try {
            jsonValue = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonValue;
    }

    private boolean checkForMandatoryHeaders(MessageHeaders messageHeaders, String message) {

        LOGGER.info("Checking for mandatory headers for '{}'", message);
        if (messageHeaders.get(Constants.PAYMENT_ID) == null) {
            LOGGER.info("PaymentId is not present for '{}'", message);
            return false;
        }
        if (messageHeaders.get(Constants.ORDER_ID) == null) {
            LOGGER.info("OrderId is not present for '{}'", message);
            return false;
        }
        return true;
    }
}

