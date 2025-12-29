package com.example.setter.injection;

public class Car {
    private Specification specification;

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

//    public Car(Specification specification) {
//        this.specification = specification;
//    }
    
    public Specification displayCarDetails(){
        System.out.println("Car Details : " + specification);
        return  specification;
    }
}
