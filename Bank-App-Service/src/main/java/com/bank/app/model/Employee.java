package com.bank.app.model;

public record Employee(Integer empId, String empName) {

    @Override
    public Integer empId() {
        return empId;
    }

    @Override
    public String empName() {
        return empName;
    }

    


}
