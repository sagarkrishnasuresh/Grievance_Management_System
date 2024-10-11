package com.example.grievance_management.service;

import com.example.grievance_management.model.User;
import com.example.grievance_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public ResponseEntity<?> login(String email, String password) {
        User user = userRepository.findByUserEmailAndUserPassword(email,password);
        if (user != null && user.getUserPassword().equals(password)) {
            return ResponseEntity.ok(user.getRole());
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
