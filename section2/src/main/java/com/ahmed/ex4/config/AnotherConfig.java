package com.ahmed.ex4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnotherConfig {

    @Bean
    public String helloWorld() {
        return "hello world";
    }

}
