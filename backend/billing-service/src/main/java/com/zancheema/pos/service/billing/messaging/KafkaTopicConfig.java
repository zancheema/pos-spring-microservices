package com.zancheema.pos.service.billing.messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String TOPIC_STOCK_ADDED = "stock-added";
    public static final String TOPIC_STOCK_SOLD = "stock-sold";

    @Bean
    public NewTopic stockAddedTopic() {
        return TopicBuilder.name(TOPIC_STOCK_ADDED)
                .build();
    }

    @Bean
    public NewTopic stockSoldTopic() {
        return TopicBuilder.name(TOPIC_STOCK_SOLD)
                .build();
    }
}
