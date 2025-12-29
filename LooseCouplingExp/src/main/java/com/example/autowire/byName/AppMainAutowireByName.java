package com.example.autowire.byName;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMainAutowireByName {
    public static void main(String[] args) {
        ApplicationContext applicationContextNew = new ClassPathXmlApplicationContext("applicationContextForAutowireByName.xml");

        Car car1 = (Car) applicationContextNew.getBean("carRef");
        car1.displayCarDetails();
    }
}
