package com.ahmed.ex8.Beans;

public class Vehicle {

    private String name;


    public Vehicle(){
        System.out.println("vehicle Created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                '}';
    }
}
