package id.example.javaSpring.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(value = "/hello")
public class HelloWorld{

    @GetMapping
    public String hello(){
        return "Hello World";
    }
    
    @GetMapping("/angel")
    public String helloName(){
        return "Hello Angel!";
    }
}