package com.example.grievance_management.service;

import com.example.grievance_management.model.Assignee;
import com.example.grievance_management.repository.AssigneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssigneeService {
    @Autowired
    private AssigneeRepository assigneeRepository;

    public ResponseEntity<?> login(String email, String password) {
        Assignee assignee = assigneeRepository.findByAssigneeEmail(email);
        if (assignee != null && assignee.getAssigneePassword().equals(password)) {
            return ResponseEntity.ok(assignee.getRole());
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }




    public List<Assignee> findAllAssignees() {
        return assigneeRepository.findAll();
    }
}
