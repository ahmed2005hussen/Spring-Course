package com.ahmed.jobportal.scopes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
public class RequestSessionBean {
    private String username;

    public RequestSessionBean( ) {
        System.out.println("Session Scope Bean Created");
    }
}
