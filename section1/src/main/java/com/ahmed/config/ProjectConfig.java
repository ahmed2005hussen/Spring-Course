package com.ahmed.config;

import com.ahmed.Beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class ProjectConfig {

    static int vc =1;
    static int hc =1;
    static int lc =1;

    @Bean
    // @Scope("prototype")
    // @Lazy
    public Vehicle vehicle() {
        Vehicle v = new Vehicle();
        v.setName("vvvv");
        System.out.println("count: " + vc++);
        return v;
    }

    @Bean
    public String hello() {
        System.out.println("count: " + hc++);
        return "hello";
    }

    @Bean
    Integer luckyNumber() {
        System.out.println("count: " + lc++);
        return 10;
    }


}
