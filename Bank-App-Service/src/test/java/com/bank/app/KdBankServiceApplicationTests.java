package com.bank.app;

import com.bank.app.config.AppConfig;
import com.bank.app.legacy.LegacyApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = AppConfig.class)
class KdBankServiceApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
	}

	@Test
	public void verifyBeanIsExcluded_UsingContainsBean() {
		// Method 1: Ask the context if the bean exists by its class name
		// (Spring usually names beans by uncapitalizing the class name)
		boolean beanExists = applicationContext.containsBean("legacyApiService");

		// Assert that the bean is NOT there
		assertFalse(beanExists, "The LegacyApiService should have been excluded!");
	}

	@Test
	public void verifyBeanIsExcluded_ExpectingException() {
		// Method 2: Try to fetch the bean directly from the context.
		// If it was excluded, Spring will throw a NoSuchBeanDefinitionException.
		assertThrows(NoSuchBeanDefinitionException.class, () -> {
			applicationContext.getBean(LegacyApiService.class);
		});
	}

}
