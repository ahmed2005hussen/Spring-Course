package com.ahmed.backend.controllers;

import com.ahmed.backend.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestBodyController {


    // Request: Json -> Jackson -> Java -> @RequestBody
    // Response: Java -> Jackson -> Json -> Http response
    @PostMapping("/userdto")
    public String hello(@RequestBody UserDto user) {

        return user.getName() + " " + user.getAge();
    }


}
