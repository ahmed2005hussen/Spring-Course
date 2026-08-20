package com.ahmed.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class RequestParamController {

    @GetMapping("/search")
    public String search1(@RequestParam String name,
                          @RequestParam String sex) {

        return "Name: " + name + ", Sex: " + sex;

    }

    @GetMapping("/search2")
    public String search2(@RequestParam(required = false) String name,
                          @RequestParam String sex) {

        return "Not required Name: " + name + ", Sex: " + sex;

    }

    @GetMapping("/search3")
    public String search3(@RequestParam(required = false,
                                  defaultValue = "hellllo default") String name,
                          @RequestParam String sex) {

        return "default Name: " + name + ", Sex: " + sex;

    }

    @GetMapping("/search4")
    public String search4(@RequestParam String name,
                          @RequestParam(name = "sex") String Gender) {

        return "Name: " + name + ", Sex(gender in code): " + Gender;

    }

    @GetMapping("/search5")
    public String search5(@RequestParam Map<String , String> p) {

        return "Name: " + p.get("name") + ", Sex: " + p.get("sex")
                + " , anything: " + p.get("a");

    }

}
