package com.example.consumer.service.communication.webclient.controller;

import com.example.consumer.service.communication.webclient.service.WebFluxClientProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/caller-service")
@RequiredArgsConstructor
public class WebFluxClientController {

    private final WebFluxClientProviderService webFluxClientProviderService;

    @GetMapping("/web-client-flux")
    String callApiResourceViaWebClient() {
        return webFluxClientProviderService.getProviderServiceInfo();
    }
}
