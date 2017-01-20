package com.mnit.tnt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @RequestMapping("/hi")
    public String hello() {
        return "hello, the application";
    }
}
