package com.cb.viooh.payment.service.impl;


import com.cb.viooh.payment.dto.PaymentCreateDto;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.charset.Charset;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderProducerServiceTest {

    @Autowired
    private Gson gson;


    @Autowired
    private PaymentListenerService paymentListenerService;

    public PaymentCreateDto getDTOFromFile(String jsonPath) throws IOException {
        Resource resource = new ClassPathResource(jsonPath);
        String json = IOUtils.toString(resource.getInputStream(), Charset.defaultCharset());
        return gson.fromJson(json, PaymentCreateDto.class);
    }

    @Test
    public void test_producer() {
        PaymentCreateDto paymentCreateDto = null;
        try {
            paymentCreateDto = getDTOFromFile("/component/paymentCreateRequest.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        paymentListenerService.sendOrderCreateRequest(paymentCreateDto, "paymentId", "orderId");
    }
}
