package com.ahmed.config;

import com.ahmed.Beans.Vehicle;
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
    public String hello() {
        return "hello";
    }

    @Bean
    Integer luckyNumber() {
        return 10;
    }


}
