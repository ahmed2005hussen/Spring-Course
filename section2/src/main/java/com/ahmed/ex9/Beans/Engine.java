package com.ahmed.ex9.Beans;

import org.springframework.stereotype.Component;

@Component
public class Engine {

    private String name = "hi engine";

    public Engine(){
        System.out.println("Created engine") ;

    }

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                '}';
    }
}
