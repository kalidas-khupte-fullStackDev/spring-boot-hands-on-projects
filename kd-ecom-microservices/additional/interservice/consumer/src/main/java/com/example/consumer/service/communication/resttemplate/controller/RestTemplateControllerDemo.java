package com.example.consumer.service.communication.resttemplate.controller;

import com.example.consumer.service.communication.resttemplate.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/caller-service")
@RequiredArgsConstructor
public class RestTemplateControllerDemo {

    private final RestTemplateService restTemplateService;

    @GetMapping("/rest-template")
    String callApiResource() {
        return restTemplateService.callProviderService();
    }
}
