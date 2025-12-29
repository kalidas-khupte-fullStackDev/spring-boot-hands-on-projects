package com.example.componentScan.autowired.annotations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.componentScan.autowired.annotations")
public class AppConfig {
}
