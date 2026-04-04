package com.producer.service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfiguration {

    @Bean
    public NewTopic creNewTopic(){
        return new NewTopic("new-topic", 3 , (short) 1);
    }

}
