package com.ahmed.backend.controllers;

import com.ahmed.backend.dto.UserDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class RequestEntityController {

    @PostMapping("/requestEntity")
    public String fun(RequestEntity<UserDto> request) {
        // http://localhost:8080/requestEntity?id=10&name=Ahmed

        HttpHeaders httpHeaders = request.getHeaders();

        UserDto user = request.getBody();

        HttpMethod httpMethod = request.getMethod();

        URI url = request.getUrl();

        String query = url.getQuery();

        String path = url.getPath();

        return "Http headers: " + httpHeaders +
                "\nHttp Method: " + httpMethod.toString() +
                "\nURL: " + url +
                "\nQuery: " + query +
                "\nPath: " + path +
                "\nBody: " + user;
    }
        // output :
            /*
            Http headers: [User-Location:"Cairo , Alex", User-Agent:"PostmanRuntime/7.49.1", ....]
            Http Method: POST
            URL: http://localhost:8080/requestEntity?id=10&name=Ahmed
            Query: id=10&name=Ahmed
            Path: /requestEntity
            Body: UserDto{name='ahmed', age=21}
        */
}
