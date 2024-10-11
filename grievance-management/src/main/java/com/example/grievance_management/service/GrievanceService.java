package com.example.grievance_management.service;

import com.example.grievance_management.model.Assignee;
import com.example.grievance_management.model.Grievance;
import com.example.grievance_management.model.Status;
import com.example.grievance_management.repository.AssigneeRepository;
import com.example.grievance_management.repository.GrievanceRepository;
import com.example.grievance_management.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrievanceService {

    @Autowired
    private GrievanceRepository grievanceRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private AssigneeRepository assigneeRepository;

    // Method to update the grievance status
    public Grievance updateGrievanceStatus(Long grievanceId, Long statusId) {
        Grievance grievance = grievanceRepository.findById(grievanceId).orElse(null);
        if (grievance != null) {
            Status newStatus = statusRepository.findById(statusId).orElse(null);
            if (newStatus != null) {
                grievance.setStatus(newStatus);  // Update the status
                return grievanceRepository.save(grievance);  // Save and return updated grievance
            }
        }
        return null;
    }

    public ResponseEntity<?> assignGrievanceToAssignee(Long grievanceId, Long assigneeId) {
        // Fetch the grievance by ID
        Optional<Grievance> grievanceOpt = grievanceRepository.findById(grievanceId);
        if (!grievanceOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grievance not found");
        }

        // Fetch the assignee by ID
        Optional<Assignee> assigneeOpt = assigneeRepository.findById(assigneeId);
        if (!assigneeOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignee not found");
        }

        // Assign the grievance to the assignee
        Grievance grievance = grievanceOpt.get();
        grievance.setAssignee(assigneeOpt.get());
        grievanceRepository.save(grievance);  // Save the updated grievance

        return ResponseEntity.ok(grievance);
    }
}
