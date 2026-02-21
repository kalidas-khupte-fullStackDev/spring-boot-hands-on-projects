package com.example.consumer.service.communication.restclient.controller;

import com.example.consumer.service.communication.restclient.service.RestClientProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/caller-service")
@RequiredArgsConstructor
public class RestClientController {

    private final RestClientProviderService restClientProviderService;

    @GetMapping("/rest-clients")
    String callApiResourceViaRestClient() {
        return restClientProviderService.getProviderServiceInfo();
    }
}
