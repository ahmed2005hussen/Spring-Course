package com.ahmed.ex11.Beans;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON) // optional , because this is the default
// @Scope("singleton") // or this

@Lazy // in default, spring will create the bean whenever we call it or not -> eager
public class MyService {

    public MyService(){
        System.out.println("Created Service");
    }
}
