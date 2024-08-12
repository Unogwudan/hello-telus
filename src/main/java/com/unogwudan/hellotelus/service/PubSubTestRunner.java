package com.unogwudan.hellotelus.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.unogwudan.hellotelus.dto.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PubSubTestRunner implements CommandLineRunner {

    private final PubSubTemplate pubSubTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args)  {

        List<Customer> customers = new ArrayList<>();

        // Creating 5 objects with the same payload
        customers.add(new Customer("John", "Akpa", "john.akpa@mail.com", "121 Fairview Street"));
        customers.add(new Customer("Mark", "Smith", "mark.smith@mail.com", "200 Lawrence Avenue"));
        customers.add(new Customer("Peter", "Ham", "peter.ham@mail.com", "71 old way Street"));
        customers.add(new Customer("Michael", "Boa", "michael.boa@mail.com", "8 King Street Street"));
        customers.add(new Customer("Danny", "Morgan", "danny.morgan@mail.com", "15 James Street"));

        // Print out the list to verify
        for (Customer customer : customers) {
            System.out.println(customer);
            try {
                pubSubTemplate.publish("my-topic", objectMapper.writeValueAsString(customer));
            } catch (JsonProcessingException e) {
               log.error("An error occurred while publishing message:: {}", e.getMessage());
            }
            log.info("Message published successfully.");
        }

    }
}
