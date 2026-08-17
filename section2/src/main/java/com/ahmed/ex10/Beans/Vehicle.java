package com.ahmed.ex10.Beans;

public class Vehicle {

    private String name;
    private Engine engine;

    public Vehicle(Engine engine) {
        this.engine = engine;
        System.out.println("vehicle Created");
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

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", engine=" + engine +
                '}';
    }
}
