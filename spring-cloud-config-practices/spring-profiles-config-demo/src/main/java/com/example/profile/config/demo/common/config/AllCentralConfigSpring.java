package com.example.profile.config.demo.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.profiles")
@Data
public class AllCentralConfigSpring {
    private String active;
}
