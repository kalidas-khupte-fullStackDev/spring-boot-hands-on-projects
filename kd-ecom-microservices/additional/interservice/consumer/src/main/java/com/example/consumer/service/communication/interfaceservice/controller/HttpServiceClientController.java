package com.example.consumer.service.communication.interfaceservice.controller;

import com.example.consumer.service.communication.interfaceservice.service.HttpServiceClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/caller-service")
@RequiredArgsConstructor
public class HttpServiceClientController {

    private final HttpServiceClientProvider httpServiceClientProvider;

    @GetMapping("/http-interface/restclient")
    String callApiResourceViaRestClient() {
        return httpServiceClientProvider.getRestClientBasedProviderServiceInfo();
    }

    @GetMapping("/http-interface/web-client")
    String callApiResourceViaWebClientClient() {
        return httpServiceClientProvider.getWebClientBasedProviderServiceInfo();
    }
}
