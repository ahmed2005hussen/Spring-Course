package com.ahmed.ex1.config;

import com.ahmed.ex1.Beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public Vehicle vehicle() {
        Vehicle v = new Vehicle();
        v.setName("vvvv");

        return v;
    }

    @Bean
    public Vehicle vehicle1() {
        Vehicle v = new Vehicle();
        v.setName("aaaa");

        return v;
    }

    @Bean
    public Vehicle vehicle2() {
        Vehicle v = new Vehicle();
        v.setName("vvvv");

        return v;
    }

    @Bean
    public String hello() {
        return "hello";
    }

    @Bean
    Integer luckyNumber() {
        return 10;
    }


}
