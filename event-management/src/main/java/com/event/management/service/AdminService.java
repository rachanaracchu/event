package com.event.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.event.management.model.Admin;
import com.event.management.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register new admin
    public Admin registerAdmin(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        Admin admin = new Admin(username, hashedPassword);
        return adminRepository.save(admin);
    }

    // Authenticate admin login
    public boolean authenticate(String username, String password) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get(); // ✅ safe after isPresent check
            return passwordEncoder.matches(password, admin.getPassword());
        }
        return false;
    }

    // Get admin by username
    public Admin getByUsername(String username) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        return adminOpt.orElse(null); // ✅ safe and standard
    }
}
