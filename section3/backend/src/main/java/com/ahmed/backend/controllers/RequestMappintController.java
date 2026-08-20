package com.ahmed.backend.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // on top of class of we have common path
public class RequestMappintController {

    @RequestMapping("/hello1")
    public String hello1() {
        return "Hello with Request Mapping accept all the http methods";
    }

    @RequestMapping(path = {"/hello2", "hello2a"},
            method = {RequestMethod.GET, RequestMethod.POST})
    public String hello2() {
        return "Hello with Request Mapping, accept some http methods";
    }

    @RequestMapping(path = {"/hello3"},
            method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public String hello3() {
        return "with produces ";
    }

    @RequestMapping(path = {"/hello4"},
            method = {RequestMethod.GET, RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public String hello4() {
        return " with  consumes";
    }


}
