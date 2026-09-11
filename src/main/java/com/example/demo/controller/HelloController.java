package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple controller demonstrating the basics of @RestController and @GetMapping.
 *
 * Node.js equivalent:
 *   app.get('/hello', (req, res) => res.send('Hello from Spring Boot!'));
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }

    @GetMapping("/")
    public String root() {
        return "Spring Boot Lab is running. Visit /hello or /users";
    }
}
