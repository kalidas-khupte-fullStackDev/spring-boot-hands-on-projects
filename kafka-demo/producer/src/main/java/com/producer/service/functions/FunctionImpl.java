package com.producer.service.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class FunctionImpl {

    @Bean
    public Function<String, String> uppercase(){
        return String::toUpperCase;
    }
}
