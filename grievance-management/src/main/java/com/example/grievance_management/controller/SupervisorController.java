package com.example.grievance_management.controller;

import com.example.grievance_management.model.Supervisor;
import com.example.grievance_management.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supervisors")
@CrossOrigin(origins = "http://localhost:3000")  // Enable CORS for this controller
public class SupervisorController {
    @Autowired
    private SupervisorService supervisorService;

    @PostMapping("/login")
    public ResponseEntity<?> loginSupervisor(@RequestBody Supervisor loginData) {
        return supervisorService.login(loginData.getSupervisorEmail(), loginData.getSupervisorPassword());
    }
}
