package com.example.grievance_management.controller;

import com.example.grievance_management.model.Assignee;
import com.example.grievance_management.service.AssigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignees")
@CrossOrigin(origins = "http://localhost:3000")  // Enable CORS for this controller
public class AssigneeController {
    @Autowired
    private AssigneeService assigneeService;

    @GetMapping("/all")
    public ResponseEntity<List<Assignee>> getAllAssignees() {
        List<Assignee> assignees = assigneeService.findAllAssignees();
        if (assignees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(assignees);
        }
        return ResponseEntity.ok(assignees);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAssignee(@RequestBody Assignee loginData) {
        return assigneeService.login(loginData.getAssigneeEmail(), loginData.getAssigneePassword());
    }
}
