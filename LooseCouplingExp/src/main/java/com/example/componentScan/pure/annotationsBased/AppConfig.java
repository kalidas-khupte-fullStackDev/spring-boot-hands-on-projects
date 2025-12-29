package com.example.componentScan.pure.annotationsBased;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.componentScan.pure.annotationsBased")
public class AppConfig {
}
