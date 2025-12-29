package com.example.componentScan.autowired.annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMainAutowireViaAnnotation {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        Project project = applicationContext.getBean("project", Project.class);
        System.out.println("Project Details" + project);
        Employee employee = applicationContext.getBean("employee", Employee.class);
        System.out.println("Employee Details" + employee);
    }
}
