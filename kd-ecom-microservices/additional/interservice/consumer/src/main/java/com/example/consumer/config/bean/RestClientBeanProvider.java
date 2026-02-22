package com.example.consumer.config.bean;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientBeanProvider {

    //    private static final String baseURL = "http://localhost:8081/api";
    private static final String baseURL = "http://provider-service";

    // 1. Provide a default, unmodified builder as the @Primary choice for internal framework use
    @Bean
    @Primary
    public RestClient.Builder defaultRestClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    @LoadBalanced
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public RestClient getRestClient(@LoadBalanced RestClient.Builder builder) {
        return builder.baseUrl(baseURL).build();
    }
}
