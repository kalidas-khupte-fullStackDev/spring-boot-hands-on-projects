package com.example.consumer.service.communication.restclient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class RestClientProviderService {

    private final RestClient restClient;

    public String getProviderServiceInfo() {
        System.out.println("Sent Packet reqeust from consumer service via REST Clients");
        String res = "Sent Packet reqeust from consumer service via REST Clients";
        String providerRes = restClient.get().uri("/ping-service").retrieve().body(String.class);;
        return String.join(" ", res , providerRes);
    }
}
