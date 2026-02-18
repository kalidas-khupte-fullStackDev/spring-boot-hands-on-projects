package com.example.profile.config.demo.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "build")
@Data
public class AllCentralConfig {
    private Integer id;
    private Double version;
    private String name;
}
