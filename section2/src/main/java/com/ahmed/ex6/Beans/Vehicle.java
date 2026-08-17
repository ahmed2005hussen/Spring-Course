package com.ahmed.ex6.Beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
// using this interfaces is not the best approach, but it is work
public class Vehicle implements InitializingBean , DisposableBean {

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

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("This function from Spring frame work\n " +
                "from interface: InitializingBean. \n" +
                "after the bean is created this function will call ");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("This function from Spring frame work\n " +
                "from interface: DisposableBean. \n" +
                "before the bean destroyed this function will call ");
    }
}
