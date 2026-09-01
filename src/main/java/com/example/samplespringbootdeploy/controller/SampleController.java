package com.example.samplespringbootdeploy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    // Additional endpoints can be added here
}