package com.example.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/ping-service")
public class ProviderController {

    @Value("${server.port}")
    private String serverPort;

    private final String serviceInstance = UUID.randomUUID().toString();

    @GetMapping
    String getApiResource(){
        System.out.println("Received Packet reqeust on port: "+ serverPort);
        return String.join("\n", "Response from service instance Id:" , serviceInstance, "Service port number:", serverPort);
    }
}
