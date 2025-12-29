package com.example.autowire.byType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMainAutowireByType {
    public static void main(String[] args) {
        ApplicationContext applicationContextNew = new ClassPathXmlApplicationContext("applicationContextForAutowireByType.xml");

        Car car1 = (Car) applicationContextNew.getBean("carRef");
        car1.displayCarDetails();
    }
}
