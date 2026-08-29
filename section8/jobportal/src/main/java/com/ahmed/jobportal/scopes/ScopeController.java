package com.ahmed.jobportal.scopes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scope")
@RequiredArgsConstructor
public class ScopeController {

    private final RequestScopeBean requestScopeBean;
    private final RequestSessionBean requestSessionBean;
    private final ApplicationScopedBean applicationScopedBean;

    @GetMapping("/request")
    public ResponseEntity<String> testRequestScope() {

        requestScopeBean.setUsername("ahmed");
        return ResponseEntity.ok().body(requestScopeBean.getUsername());
    }

    @GetMapping("/session")
    public ResponseEntity<String> testSessionScope() {

        requestSessionBean.setUsername("ahmed");
        return ResponseEntity.ok().body(requestSessionBean.getUsername());
    }

    @GetMapping("/test")
    public ResponseEntity<Integer> testScope() {
        return ResponseEntity.ok().body(applicationScopedBean.getVisitor());
    }
   @GetMapping("/app")
    public ResponseEntity<Integer> testAppScope() {
        applicationScopedBean.increment();
        return ResponseEntity.ok().body(applicationScopedBean.getVisitor());
    }



}
