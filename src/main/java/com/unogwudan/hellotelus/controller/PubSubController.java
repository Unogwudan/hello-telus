package com.unogwudan.hellotelus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.unogwudan.hellotelus.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PubSubController {

    private final PubSubTemplate pubSubTemplate;
    private final ObjectMapper objectMapper;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody Customer customer) throws JsonProcessingException {
        pubSubTemplate.publish("my-topic", objectMapper.writeValueAsString(customer));
        return "Message published.";
    }
}
