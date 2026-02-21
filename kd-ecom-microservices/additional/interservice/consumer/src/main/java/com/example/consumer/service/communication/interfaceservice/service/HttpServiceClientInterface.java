package com.example.consumer.service.communication.interfaceservice.service;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

//@HttpExchange(url = "/repos/{owner}/{repo}", accept = "application/json")
@HttpExchange()
public interface HttpServiceClientInterface {

    @GetExchange(url = "/ping-service")
    String getInstanceInfo();
}
