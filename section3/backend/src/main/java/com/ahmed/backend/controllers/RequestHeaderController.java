package com.ahmed.backend.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RequestHeaderController {

    @GetMapping("/header1")
    public String header1(@RequestHeader("Content-Type") String nameHeader){
        return "Get one header: " + nameHeader;

    }

    @GetMapping("/header2")
    public String header2(@RequestHeader(name = "User-Location" ,
            required = false) String nameHeader){

        return "Not required, Get one header: " + nameHeader;

    }

    @GetMapping("/header3")
    public String header3(@RequestHeader(name = "User-Location" ,
            defaultValue = "Not obtain") String nameHeader){

        return "With default, Get one header: " + nameHeader;

    }

    @GetMapping("/header4")
    public String header4(@RequestHeader Map<String , String> p){

        return "With Map, Get one header: " + p.get("Content-Type") + " , Location: "
                + p.get("User-Location");

    }

    @GetMapping("/header5")
    public String header5(@RequestHeader HttpHeaders h){

        // "Accept" : ["application/json" , "text/plain"]

        // h.get() -> list of String
        // h.getFirst() -> first value in String
        return "With Map, Get one header: " + h.get("Content-Type") + " , Location: "
                + h.get("User-Location");

    }

}
