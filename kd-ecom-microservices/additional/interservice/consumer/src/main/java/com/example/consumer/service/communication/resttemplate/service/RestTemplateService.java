package com.example.consumer.service.communication.resttemplate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestTemplateService {

    @Value("${server.port}")
    private String serverPort;
    private final String serviceInstance = UUID.randomUUID().toString();
    private static final String PROVIDER_SERVICE_URI = "http://localhost:8081/api";

    private final RestTemplate restTemplate;

    public String callProviderService() {
        System.out.println("Sent Packet reqeust from port: " + serverPort);
        String finalURI = String.join("/", PROVIDER_SERVICE_URI, "ping-service");
        String providerResponse = restTemplate.getForObject(finalURI, String.class);
        return String.join("\n", "Response from provider service:", providerResponse ,"request sent from caller serviceInstance:", serviceInstance, "via server port :", serverPort);
    }

}
