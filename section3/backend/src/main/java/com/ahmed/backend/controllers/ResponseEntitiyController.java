package com.ahmed.backend.controllers;

import com.ahmed.backend.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseEntitiyController {

    @GetMapping("/responseEntity")
    public ResponseEntity<UserDto> userResponse(){

        UserDto user = new UserDto();
        user.setName("ahmed");
        user.setAge(22);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location" , "el giza")
                .body(user);

    }

}
