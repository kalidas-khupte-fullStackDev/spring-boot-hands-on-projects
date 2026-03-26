package com.ecommerce.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RestController
@RequestMapping("/fallback")
public class ServiceDownFallBackController {

    @GetMapping("/users")
    public ResponseEntity<?> fallBackUserService() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Collections.singletonList("User service is unavailable, please try after sometime"));
    }

    @GetMapping("/products")
    public ResponseEntity<?> fallBackProductService() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Collections.singletonList("Product service is unavailable, please try after sometime"));
    }
    @GetMapping("/carts")
    public ResponseEntity<?> fallBackCartService() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Collections.singletonList("Cart service is unavailable, please try after sometime"));
    }
    @GetMapping("/orders")
    public ResponseEntity<?> fallBackOrderService() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Collections.singletonList("Order service is unavailable, please try after sometime"));
    }

}
