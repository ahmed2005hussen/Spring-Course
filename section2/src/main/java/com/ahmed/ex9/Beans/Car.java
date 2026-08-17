package com.ahmed.ex9.Beans;


import org.springframework.stereotype.Component;

@Component
public class Car {

    private String name = "hi Car";

    // @Autowired
    private Engine engine;

    // @Autowired
    // if we have one constructor we may not write @Autowired, and it will works
    public Car(Engine engine){
        this.engine = engine;
        System.out.println("Created Car");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Engine getEngine() {
        return engine;
    }

    // @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", engine=" + engine +
                '}';
    }
}
