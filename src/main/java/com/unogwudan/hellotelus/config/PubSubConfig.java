package com.unogwudan.hellotelus.config;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import com.google.cloud.spring.pubsub.integration.AckMode;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

@Slf4j
@Configuration
public class PubSubConfig {

    @Bean
    public MessageChannel myInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(@Qualifier("myInputChannel") MessageChannel inputChannel, PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, "my-subscription");
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.MANUAL);
        return adapter;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new MappingJackson2MessageConverter();
    }
}
