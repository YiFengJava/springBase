package com.server;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello/{message}",method = RequestMethod.GET)
    public String hello(@PathVariable("message") String message){
        return "Hello " + message + "!";
    }

}
