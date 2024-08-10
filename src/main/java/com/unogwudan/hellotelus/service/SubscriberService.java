package com.unogwudan.hellotelus.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.unogwudan.hellotelus.dto.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriberService {

    private final ObjectMapper objectMapper;


    @Bean
    @ServiceActivator(inputChannel = "myInputChannel")
    public MessageHandler messageReceiver() {
        return message -> {

            BasicAcknowledgeablePubsubMessage originalMessage = (BasicAcknowledgeablePubsubMessage) message.getHeaders().get(GcpPubSubHeaders.ORIGINAL_MESSAGE);
            if (originalMessage != null) {
                String payload = originalMessage.getPubsubMessage().getData().toStringUtf8();
                log.info("Message arrived! Payload: {}", payload);
                try {
                    Customer customer = objectMapper.readValue(payload, Customer.class);
                    log.info("Customer Data: {}", customer);
                } catch (JsonProcessingException e) {
                    log.error("Exception occurred while reading message: {}", e.getMessage());
                }
                originalMessage.ack();
            }
        };
    }
}
