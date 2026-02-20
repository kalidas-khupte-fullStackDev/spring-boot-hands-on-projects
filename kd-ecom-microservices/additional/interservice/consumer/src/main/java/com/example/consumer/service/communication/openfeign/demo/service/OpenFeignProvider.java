package com.example.consumer.service.communication.openfeign.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient( name = "prover-service", url= "http://localhost:8081/api")
public interface OpenFeignProvider {

    @GetMapping("/ping-service")
    String callProverService();

}
