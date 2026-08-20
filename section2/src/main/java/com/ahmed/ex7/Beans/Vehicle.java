package com.ahmed.ex7.Beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Vehicle {

    public Vehicle(){
        System.out.println("Created ");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void hello(){
        System.out.println("hello");
    }

    @PostConstruct // from jakarta annotation api, so needs the dep in pom
    public void initBean() {
        System.out.println("This function from jakarta Annotation api \n " +
                "from interface: InitializingBean. \n" +
                "after the bean is created this function will call ");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("This function from jakarta Annotation api  \n " +
                "from interface: DisposableBean. \n" +
                "before the bean destroyed this function will call ");
    }
}
