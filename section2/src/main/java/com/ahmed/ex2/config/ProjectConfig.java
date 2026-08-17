package com.ahmed.ex2.config;

import com.ahmed.ex2.Beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@Configuration
public class ProjectConfig {


    @Bean(name = "v1")
    public Vehicle vehicle() {
        Vehicle v = new Vehicle();
        v.setName("vvvv");

        return v;
    }
    @Bean(value = "v2")
    public Vehicle vehicle1() {
        Vehicle v = new Vehicle();
        v.setName("aaaa");

        return v;
    }

    @Bean("v3")
    @Description("this is an description for the bean")
    public Vehicle vehicle2() {
        Vehicle v = new Vehicle();
        v.setName("bbbb");

        return v;
    }

    @Bean({"v4" , "vehicle 4" , "vv" , "alias"})
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
