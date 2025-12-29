package com.example.constructor.injection;

public class Car {
    
    private Specification specification;


    public Car(Specification specification) {
        this.specification = specification;
    }
    
    public Specification displayCarDetails(){
        System.out.println("Car Details : " + specification);
        return  specification;
    }
}
