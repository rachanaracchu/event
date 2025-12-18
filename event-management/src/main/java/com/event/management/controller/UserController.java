package com.event.management.controller;

import com.event.management.model.User;
import com.event.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String result = userService.register(user);
        if (result.equals("User registered successfully!")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        User existing = userService.login(user.getEmail(), user.getPassword());
        Map<String, String> response = new HashMap<>();
        if (existing != null) {
            response.put("email", existing.getEmail());
            response.put("role", existing.getRole());
        } else {
            response.put("error", "Invalid email or password");
        }
        return response;
    }

    @GetMapping("/test")
    public String test() {
        return "Backend is running!";
    }
}
