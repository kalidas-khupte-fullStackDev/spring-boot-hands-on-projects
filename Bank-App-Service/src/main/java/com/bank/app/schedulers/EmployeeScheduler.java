package com.bank.app.schedulers;

import com.bank.app.model.Employee;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EmployeeScheduler {

    private final Set<Employee> employeeList = new HashSet<>();
    private final Map<Integer, Employee> employeeListMap = new HashMap<>();

    EmployeeScheduler(){
        Employee e1 = new Employee(123, "Kalidas");
        Employee e2 = new Employee(123, "Kalidas");
        Employee e3 = new Employee(124, "Feroxa");
        Employee e4 = new Employee(125, "amerr");
        this.employeeList.addAll(List.of(e1,e2,e3,e4));

        employeeListMap.put(e1.empId(), e1);
        employeeListMap.put(e2.empId(), e2);
        System.out.println(employeeListMap.getOrDefault(123, e3));
    }

    @Scheduled(fixedRate = 600)
    public void printEmpList(){
        System.out.println("Number employee in list : " + employeeList.size());
    }
}
