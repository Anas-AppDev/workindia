package com.example.RailwayManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.RailwayManagementSystem.model.User;
import com.example.RailwayManagementSystem.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User newUser = userService.register(user);
            return ResponseEntity.ok("User registered successfully! ID: " + newUser.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String jwt = userService.login(user.getEmail(), user.getPassword());

        if (jwt != null) {
            return ResponseEntity.ok("{\"token\": \"" + jwt + "\"}");
        } else {
            return ResponseEntity.badRequest().body("Invalid Credentials");
        }
    }
}