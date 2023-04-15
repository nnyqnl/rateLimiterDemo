package com.example.rateLimiter.controller;

import com.example.rateLimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rateLimiter")
public class TestController {
    @RateLimiter(limitCount = 5, time = 60)
    @GetMapping("/test")
    public String test() {
        return "test success!";
    }

    @RateLimiter(limitCount = 5, time = 60)
    @GetMapping("/test2")
    public String test2() {
        return "test2 success!";
    }
}
