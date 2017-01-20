package com.mnit.tnt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lihe on 1/19/17.
 */

@RestController
@RequestMapping("/private")
public class PrivateController {

    @RequestMapping("/hi")
    public String hello() {
        return "hello, I am private";
    }
}
