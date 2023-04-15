//package com.example.rateLimiter.controller;
//
//import com.example.rateLimiter.entity.User;
//import com.example.rateLimiter.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//public class LoginController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/login")
//    public String login(String username, String password) {
//
//        if (username == null || "".equals(username)) {
//            return "username is required";
//        }
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        Example<User> example = Example.of(user);
//        Optional<User> one = userRepository.findOne(example);
//        if (one.isPresent()) {
//            String token = UUID.randomUUID().toString();
//            User u = one.get();
//            u.setToken(token);
//            userRepository.save(u);
//            return token;
//        } else {
//            return "username or password error";
//        }
//
//
//    }
//
//
//}
