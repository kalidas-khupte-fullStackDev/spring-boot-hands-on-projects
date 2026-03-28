package com.ecommerce.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(20, 30, 1);
    }

    @Bean
    KeyResolver hostnameResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostName()));
    }

    @Bean
    public RouteLocator customRoutesLocator(RouteLocatorBuilder builder) {
        return builder.routes().route("user-service", r -> r
                        // Capture everything after /users/ into a variable called 'segment'
                        .path("/api/users", "/api/users/**").filters(gatewayFilterSpec -> gatewayFilterSpec.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()).setKeyResolver(hostnameResolver())).retry(retryConfig -> retryConfig.setMethods(HttpMethod.GET).setRetries(5)).circuitBreaker(config -> config.setName("commonCircuitBreakerService").setFallbackUri("forward:/fallback/users")))
                        // Plop that captured segment onto the end of the new path
//                        .filters(f -> f.rewritePath("/users(?<segment>/?.*)", "/api/users${segment}"))
                        .uri("lb://user-service")).route("product-service", r -> r
                        // Capture everything after /users/ into a variable called 'segment'
                        .path("/api/products", "/api/products/**").filters(gatewayFilterSpec -> gatewayFilterSpec.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()).setKeyResolver(hostnameResolver())).retry(retryConfig -> retryConfig.setMethods(HttpMethod.GET).setRetries(5)).circuitBreaker(config -> config.setName("commonCircuitBreakerService").setFallbackUri("forward:/fallback/products")))
                        // Plop that captured segment onto the end of the new path
//                        .filters(f -> f.rewritePath("/products(?<segment>/?.*)", "/api/products${segment}"))
                        .uri("lb://product-service"))
//                .route("order-service", r -> r
//                        // Capture 'orders' or 'carts' into {prefix}, and the rest into {segment}
//                        .path("/orders", "/orders/**", "/cart", "/cart/**")
//                        // Rebuild the path using both variables
//                        .filters(f -> f.rewritePath("/(?<prefix>orders|cart)(?<segment>/?.*)",
//                                "/api/${prefix}${segment}"))
//                        .uri("lb://order-service"))
                .route("order-service-carts", r -> r
                        // Capture 'orders' or 'carts' into {prefix}, and the rest into {segment}
                        .path("/api/cart", "/api/cart/**").filters(gatewayFilterSpec -> gatewayFilterSpec.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()).setKeyResolver(hostnameResolver())).retry(retryConfig -> retryConfig.setMethods(HttpMethod.GET).setRetries(5)).circuitBreaker(config -> config.setName("commonCircuitBreakerService").setFallbackUri("forward:/fallback/carts")))
                        // Rebuild the path using both variables
//                        .filters(f -> f.rewritePath("/cart(?<segment>/?.*)",
//                                "/api/cart${segment}"))
                        .uri("lb://order-service")).route("order-service-main", r -> r
                        // Capture 'orders' or 'carts' into {prefix}, and the rest into {segment}
                        .path("/api/orders", "/api/orders/**").filters(gatewayFilterSpec -> gatewayFilterSpec.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()).setKeyResolver(hostnameResolver())).retry(retryConfig -> retryConfig.setMethods(HttpMethod.GET).setRetries(5)).circuitBreaker(config -> config.setName("commonCircuitBreakerService").setFallbackUri("forward:/fallback/orders")))
                        // Rebuild the path using both variables
//                        .filters(f -> f.rewritePath("/orders(?<segment>/?.*)",
//                                "/api/orders${segment}"))
                        .uri("lb://order-service")).route("eureka-registry-service", r -> r
                        // Capture everything after /users/ into a variable called 'segment'
                        .path("/eureka/main")
                        // Plop that captured segment onto the end of the new path
                        .filters(f -> f.setPath("/")).uri("http://localhost:8761")).route("eureka-registry-service-statice", r -> r
                        // Capture everything after /users/ into a variable called 'segment'
                        .path("/eureka/**").uri("http://localhost:8761")).build();
    }
}
