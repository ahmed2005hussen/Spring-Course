package com.ahmed.jobportal.scopes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
@Getter @Setter
public class ApplicationScopedBean {

    private int visitor;

    public ApplicationScopedBean(){
        System.out.println("Application scoped bean created");

    }

    public void increment(){
        visitor++; 
    }




}
