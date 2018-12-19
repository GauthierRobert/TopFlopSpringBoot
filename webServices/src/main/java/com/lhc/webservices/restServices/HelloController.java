package com.lhc.webservices.restServices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String welcome() {//Welcome page, non-rest
        return "Welcome to RestTemplate Example.";
    }

    @RequestMapping("/hello")
    public String message(@RequestParam String player) {//REST Endpoint.

        String msg = player + "Hello " + player;
        return msg;
    }
}