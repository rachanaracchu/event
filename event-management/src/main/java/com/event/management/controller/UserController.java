package com.event.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.event.management.model.User;
import com.event.management.repository.UserRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // ===================== Registration =====================
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "User with this email already exists";
        }
        userRepository.save(user);
        return "User registered successfully";
    }

    // ===================== Login =====================
    @PostMapping("/login")
    public UserDTO loginUser(@RequestBody LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(req.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Return only necessary info for login
        return new UserDTO(user.getEmail(), user.getFullName(), user.getRole());
    }

    // ===================== Get Profile =====================
    @GetMapping("/{email}")
    public User getUserProfile(@PathVariable String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ===================== Update Profile =====================
    @PutMapping("/update")
    public String updateUserProfile(@RequestBody User updatedUser) {
        User user = userRepository.findByEmail(updatedUser.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(updatedUser.getFullName());
        user.setPhone(updatedUser.getPhone());
        user.setCity(updatedUser.getCity());
        user.setRole(updatedUser.getRole());

        userRepository.save(user);
        return "Profile updated successfully";
    }

    // ===================== Change Password =====================
    @PutMapping("/change-password")
    public String changePassword(@RequestBody PasswordRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(req.getNewPassword());
        userRepository.save(user);
        return "Password changed successfully";
    }

    // ===================== Upload Profile Image =====================
    @Transactional
    @PostMapping("/upload-photo")
    public ResponseEntity<String> uploadPhoto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("email") String email) {

        System.out.println("UPLOAD HIT");
        System.out.println("Email from frontend: " + email);
        System.out.println("File size: " + file.getSize());

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        try {
            user.setProfileImage(file.getBytes());
            userRepository.save(user);
            return ResponseEntity.ok("Profile photo uploaded");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Upload failed");
        }
    }






    // ===================== DTOs =====================
    public static class UserDTO {
        private String email;
        private String fullName;
        private String role;

        public UserDTO(String email, String fullName, String role) {
            this.email = email;
            this.fullName = fullName;
            this.role = role;
        }

        public String getEmail() { return email; }
        public String getFullName() { return fullName; }
        public String getRole() { return role; }
    }

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class PasswordRequest {
        private String email;
        private String newPassword;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    }
}
