package com.example.autowire.byConstructor;

public class Car {

    private Specification specification;

    public void setSpecification(Specification specification) {
        System.out.println("Setter call via autowire by Type - 0");
        this.specification = specification;
    }

    public Specification displayCarDetails(){
        System.out.println("Car Details via Specification obj: " + specification.toString());
        return  specification;
    }
}
