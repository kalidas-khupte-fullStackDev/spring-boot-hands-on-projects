package com.example.componentScan.pure.annotationsBased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMainCompScanPureAnnotationBased {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        Employee employee = applicationContext.getBean("employee", Employee.class);
        System.out.println("Employee Details" + employee);
    }
}
