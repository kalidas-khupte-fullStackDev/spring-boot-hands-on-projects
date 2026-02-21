package com.example.consumer.config.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientBeanProvider {

    private static final String baseURL = "http://localhost:8081/api";

    @Bean
    public RestClient getRestClient(RestClient.Builder builder) {
        return builder.baseUrl(baseURL).build();
    }
}
