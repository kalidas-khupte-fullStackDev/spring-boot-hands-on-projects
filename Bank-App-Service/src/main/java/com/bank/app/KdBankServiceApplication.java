package com.bank.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
		basePackages = "com.bank.app",
		excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
				pattern = "com.bank.app.legacy.*"))
public class KdBankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KdBankServiceApplication.class, args);
	}

}
