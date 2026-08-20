package com.ahmed.backend.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dummy/users")
public class PathVariableController {

    @GetMapping("/{userId}/posts")
    public String hello(@PathVariable Integer userId) {

        return ("Hello with path variable: " + userId + " user id , ");
    }

    @GetMapping({"/{userId}/posts/{postId}", "/{userId}/posts"})
    public String hello1(@PathVariable Integer userId,
                         @PathVariable(required = false) Integer postId) {

        String message;
        if (postId == null) {
            message = "Hello with path variables: " + userId + " user id ," +
                    " post id = null, as it is not required ";
        } else {
            message = "Hello with path variables: " + userId + " user id ," +
                    " post id: " + postId;
        }
        return message;
    }

    @GetMapping({"/{userId}"})
    public String hello2(@PathVariable(name = "userId") Integer customerId) {

        return ("Hello with path variables: " + customerId +
                " user id, by naming it customer id ");
    }


    @GetMapping("/{id}/{postId}/{classId}")
    public String hello3(@PathVariable Map<String , Integer> p){

        return "hello id " + p.get("id") +
                ", postId " + p.get("postId")+ ", classId " + p.get("classId");

    }

}
