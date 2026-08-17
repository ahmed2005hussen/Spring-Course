package com.ahmed.ex10.Beans;

import org.springframework.stereotype.Component;

@Component
public class Engine {

    private String name;

    public Engine() {
        System.out.println("Created engine");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                '}';
    }
}
