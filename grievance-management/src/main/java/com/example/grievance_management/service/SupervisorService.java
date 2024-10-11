package com.example.grievance_management.service;

import com.example.grievance_management.model.Supervisor;
import com.example.grievance_management.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SupervisorService {
    @Autowired
    private SupervisorRepository supervisorRepository;

    public ResponseEntity<?> login(String email, String password) {
        Supervisor supervisor = supervisorRepository.findBySupervisorEmail(email);
        if (supervisor != null && supervisor.getSupervisorPassword().equals(password)) {
            return ResponseEntity.ok(supervisor.getRole());
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
