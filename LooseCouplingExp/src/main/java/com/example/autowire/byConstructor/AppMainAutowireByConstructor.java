package com.example.autowire.byConstructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMainAutowireByConstructor {
    public static void main(String[] args) {
        ApplicationContext applicationContextNew = new ClassPathXmlApplicationContext("applicationContextForAutowireByType.xml");

        Car car1 = (Car) applicationContextNew.getBean("carRef");
        car1.displayCarDetails();
    }
}
