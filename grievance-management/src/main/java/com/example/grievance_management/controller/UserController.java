package com.example.grievance_management.controller;

import com.example.grievance_management.dto.GrievanceDTO;
import com.example.grievance_management.model.Grievance;
import com.example.grievance_management.model.User;
import com.example.grievance_management.repository.GrievanceRepository;
import com.example.grievance_management.repository.UserRepository;
import com.example.grievance_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GrievanceRepository grievanceRepository;

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginData) {
        // Verify the user's email and password
        User user = userRepository.findByUserEmailAndUserPassword(loginData.getUserEmail(), loginData.getUserPassword());

        if (user != null) {
            // Return the userId and role
            Map<String, Object> response = new HashMap<>();
            response.put("userId", user.getUserId());
            response.put("role", "user"); // Assuming role is "user" for now

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    @GetMapping("/user/{userId}/grievances")
    public ResponseEntity<List<GrievanceDTO>> getGrievancesByUser(@PathVariable Long userId) {
        List<Grievance> grievances = grievanceRepository.findByUser_UserId(userId);

        if (grievances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        // Map Grievance entities to GrievanceDTOs
        List<GrievanceDTO> grievanceDTOs = grievances.stream().map(grievance -> {
            GrievanceDTO dto = new GrievanceDTO();
            dto.setGrievanceId(grievance.getGrievanceId());
            dto.setGrievanceType(grievance.getGrievanceType());
            dto.setDescription(grievance.getDescription());
            dto.setStatus(grievance.getStatus().getStatusUpdate());  // Use getStatusUpdate() to map status
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(grievanceDTOs);
    }



}
