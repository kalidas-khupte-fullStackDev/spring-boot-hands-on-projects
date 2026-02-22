package com.example.consumer.service.communication.openfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient( name = "provider-service", url= "http://localhost:8081/api")
@FeignClient(name = "provider-service/api")
public interface OpenFeignProvider {

    @GetMapping("/ping-service")
    String callProverService();

}
