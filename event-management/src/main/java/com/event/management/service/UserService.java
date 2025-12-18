package com.event.management.service;

import com.event.management.model.User;
import com.event.management.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // REGISTER USER
    public String register(User user) {

        if (userRepo.findByEmail(user.getEmail()) != null) {
            return "Email already exists!";
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(user.getRole() == null ? "USER" : user.getRole());

        userRepo.save(user);
        return "User registered successfully!";
    }

    public User login(String email, String password) {

        User user = userRepo.findByEmail(email);

        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
