package com.ahmed.ex8.config;

import com.ahmed.ex8.Beans.Person;
import com.ahmed.ex8.Beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public Vehicle vehicle(){
        Vehicle v = new Vehicle();
        v.setName("bmw");

        return v;
    }

    @Bean
    public Person person(){
        Person p = new Person();
        p.setName("a");

        p.setVehicle(vehicle()); // without this vehicle inside person will be null
        return p;
    }

    @Bean
    public Person person1(Vehicle v){
        Person p = new Person();
        p.setName("a");

        p.setVehicle(v); // without this vehicle inside person will be null
        return p;
    }



}
