package com.example;

import com.example.bean.Person;
//import com.example.constructor.injection.Car;
import com.example.setter.injection.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationBeanContext.xml");
       // ApplicationContext applicationContextNew = new ClassPathXmlApplicationContext("applicationConstructorInjectionContext.xml");
        ApplicationContext applicationContextNew = new ClassPathXmlApplicationContext("applicationSetterInjectionContext.xml");


        Person person1 = (Person) applicationContext.getBean("personObject");
        System.out.println("Name : " + person1.getName());
        System.out.println("toString : " + person1);
        person1.setName("Aditya");
        System.out.println("Name update : " + person1.getName());
        System.out.println("toString update : " + person1);

       // Car car1 = (Car) applicationContextNew.getBean("carRef");
        Car car1 = (com.example.setter.injection.Car) applicationContextNew.getBean("carRef");
        car1.displayCarDetails();
        //System.out.println("Name : " + car1.displayCarDetails());



    }
}
