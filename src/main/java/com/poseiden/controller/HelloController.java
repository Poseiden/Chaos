package com.poseiden.controller;

import com.poseiden.repo.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private UserAccountRepo userAccountRepo;

    @RequestMapping("/hello")
    public String hello() {
        return "Greetings from Spring Project Initial With Gradle!";
    }

    @RequestMapping("/security")
    public String helloWithSecurity() {
        return "Greeting with security!";
    }
}

