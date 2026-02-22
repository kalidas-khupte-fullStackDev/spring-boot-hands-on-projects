package com.example.consumer.config.bean;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientBeanProvider {

    //private static final String baseURL = "http://localhost:8081/api";
    private static final String baseURL = "http://provider-service";

    @Bean
    @Primary
    public WebClient.Builder defaultWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient webClient(@LoadBalanced WebClient.Builder builder) {
        return builder.baseUrl(baseURL).build();
    }
}
