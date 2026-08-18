package com.ahmed.ex11.Beans;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserSession {

    public UserSession(){
        System.out.println("Created user");
    }
}
