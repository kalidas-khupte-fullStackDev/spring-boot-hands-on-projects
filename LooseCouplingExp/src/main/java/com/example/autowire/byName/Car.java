package com.example.autowire.byName;

public class Car {
    
    private Specification specification;
    private Specification specification1;

    public void setSpecification(Specification specification) {
        System.out.println("Setter call via autowire by Name - 0");
        this.specification = specification;
    }

    public void setSpecification1(Specification specification) {
        System.out.println("Setter call via autowire by Name - 1");
        this.specification1 = specification;
    }

    public Specification displayCarDetails(){
        System.out.println("Car Details via Specification obj: " + specification.toString());
        return  specification;
    }
}
