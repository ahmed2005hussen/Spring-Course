package com.ahmed.ex1.Beans;

public class Vehicle {

    public Vehicle(){
        System.out.println("Created ");
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
