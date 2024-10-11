package com.example.grievance_management.repository;

import com.example.grievance_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom method to find user by email and password
    User findByUserEmailAndUserPassword(String userEmail, String userPassword);
}
