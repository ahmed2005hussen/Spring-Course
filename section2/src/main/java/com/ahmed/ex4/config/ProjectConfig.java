package com.ahmed.ex4.config;

import com.ahmed.ex4.Beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@Import({AnotherConfig.class})
public class ProjectConfig {

    @Primary
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
        v.setName("bbbb");

        return v;
    }

    @Bean
    public Vehicle vehicle3() {
        Vehicle v = new Vehicle();
        v.setName("eee");

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
