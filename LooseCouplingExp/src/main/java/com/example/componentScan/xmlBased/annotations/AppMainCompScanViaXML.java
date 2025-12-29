package com.example.componentScan.xmlBased.annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMainCompScanViaXML {
    public static void main(String[] args) {
        ApplicationContext applicationContextNew = new ClassPathXmlApplicationContext("componentScan.xml");

        Employee employee = applicationContextNew.getBean("employee", Employee.class);
        System.out.println("Employee Details" + employee);
    }
}
