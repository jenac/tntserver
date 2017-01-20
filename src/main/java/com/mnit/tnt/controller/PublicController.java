package com.mnit.tnt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @RequestMapping(value="/hi",  method=RequestMethod.GET, produces="application/json")
    public String hello() {
        return "hello, I am public";
    }
}
