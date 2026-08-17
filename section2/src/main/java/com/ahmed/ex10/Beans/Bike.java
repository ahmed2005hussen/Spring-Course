package com.ahmed.ex10.Beans;

public class Bike {
    private String name;

    public Bike() {
        System.out.println("Create bike");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "name='" + name + '\'' +
                '}';
    }
}
