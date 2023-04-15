package com.example.rateLimiter.controller;

import com.example.rateLimiter.entity.User;
import com.example.rateLimiter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public Map<String, String> login(String username, String password) {

        if (username == null || "".equals(username)) {
            throw new IllegalArgumentException("username is required");
        }
        if (password == null || "".equals(password)) {
            throw new IllegalArgumentException("username is required");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Example<User> example = Example.of(user);
        Optional<User> one = userRepository.findOne(example);
        if (one.isEmpty()) {
            throw new IllegalArgumentException("username or password error");
        }
        String token = UUID.randomUUID().toString();
        User u = one.get();
        u.setToken(token);
        userRepository.save(u);
        return Map.of("token", token);
    }
}
