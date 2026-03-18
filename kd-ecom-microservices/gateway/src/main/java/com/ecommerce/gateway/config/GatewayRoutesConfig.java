package com.ecommerce.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    // - id: user-service
// uri: lb://USER-SERVICE
// predicates:
// - name: Path
// args:
// patterns: /api/users/**
    @Bean
    public RouteLocator customRoutesLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r
                        // Capture everything after /users/ into a variable called 'segment'
                        .path("/users/{segment:.*}")
                        // Plop that captured segment onto the end of the new path
                        .filters(f -> f.setPath("/api/users/{segment}"))
                        .uri("lb://user-service"))
                .route("product-service", r -> r
                        // Capture everything after /users/ into a variable called 'segment'
                        .path("/products/{segment:.*}")
                        // Plop that captured segment onto the end of the new path
                        .filters(f -> f.setPath("/api/products/{segment}"))
                        .uri("lb://product-service"))
                .route("order-service", r -> r
                        // Capture everything after /users/ into a variable called 'segment'
                        .path("/orders/{segment:.*}", "/carts/{segment:.*}")
                        // Plop that captured segment onto the end of the new path
                        .filters(f -> f.setPath("/api/{segment}"))
                        .uri("lb://user-service"))
                .build();
    }
}


//gateway:
//server:
//webflux:
//routes:
//        - id: eureka-registry-service
//uri: http://localhost:8761
//predicates:
//        - name: Path
//args:
//patterns: /eureka/main
//filters:
//        - SetPath= /
//
//        - id: eureka-registry-service-static
//uri: http://localhost:8761
//predicates:
//        - name: Path
//args:
//patterns:
//        - /eureka/**
// - /eureka
// - /lastn
// - /js/**
// - /css/**
// - /fonts/**
// - /images/**
// filters:
// - StripPrefix=1
//

//
// - id: product-service
// uri: lb://PRODUCT-SERVICE
// predicates:
// - name: Path
// args:
// patterns: /api/products/**
//
// - id: order-service-cart
// uri: lb://ORDER-SERVICE
// predicates:
// - name: Path
// args:
// patterns:
// - /api/cart/**
//
// - id: order-service-orders
// uri: lb://ORDER-SERVICE
// predicates:
// - name: Path
// args:
// patterns:
// - /api/orders/**
//
