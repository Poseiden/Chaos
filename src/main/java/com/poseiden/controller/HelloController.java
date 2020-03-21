package com.poseiden.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Greetings from Spring Project Initial With Gradle!";
    }

    @GetMapping("/security")
    public String helloWithSecurity() {
        return "Greeting with security!";
    }
}

