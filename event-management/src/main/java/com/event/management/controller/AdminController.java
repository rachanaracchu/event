package com.event.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.event.management.model.Admin;
import com.event.management.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public String register(@RequestBody Admin admin) {
        if (adminService.getByUsername(admin.getUsername()) != null) {
            return "Username already exists!";
        }
        adminService.registerAdmin(admin.getUsername(), admin.getPassword());
        return "Admin registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody Admin admin) {
        boolean authenticated = adminService.authenticate(admin.getUsername(), admin.getPassword());
        if (authenticated) {
            return "Login successful!";
        }
        return "Invalid username or password!";
    }
}
