package com.example.consumer.service.communication.openfeign.controller;

import com.example.consumer.service.communication.openfeign.service.OpenFeignProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/caller-service")
@RequiredArgsConstructor
public class OpenFeignController {

    private final OpenFeignProvider openFeignProviderService;

    @GetMapping("/open-feign")
    String callApiResourceViaOpenFeign() {
        System.out.println("Sent Packet reqeust from consumer service via Open Feign");
        String res = "Sent Packet reqeust from consumer service via Open Feign";
        return String.join(" ", res, " Got response:", openFeignProviderService.callProverService());
    }
}
