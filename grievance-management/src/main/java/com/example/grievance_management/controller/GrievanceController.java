package com.example.grievance_management.controller;

import com.example.grievance_management.dto.GrievanceDTO;
import com.example.grievance_management.dto.StatusUpdateRequest;
import com.example.grievance_management.dto.UserDTO;
import com.example.grievance_management.model.Assignee;
import com.example.grievance_management.model.Grievance;
import com.example.grievance_management.model.Status;
import com.example.grievance_management.model.Supervisor;
import com.example.grievance_management.repository.*;
import com.example.grievance_management.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/grievances")
public class GrievanceController {

    @Autowired
    private GrievanceRepository grievanceRepository;

    @Autowired
    private AssigneeRepository assigneeRepository;

    @Autowired
    private GrievanceService grievanceService;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @PostMapping("/add")
    public ResponseEntity<Grievance> addGrievance(@RequestBody Grievance grievance) {
        // Find the user who is submitting the grievance
        if (grievance.getUser() != null && grievance.getUser().getUserId() != null) {
            grievance.setUser(userRepository.findById(grievance.getUser().getUserId()).orElse(null));
        }

        // Automatically assign a supervisor from the available supervisors
        List<Supervisor> supervisors = supervisorRepository.findAll();
        if (!supervisors.isEmpty()) {
            // Select a random supervisor or apply any logic to select a supervisor
            Supervisor assignedSupervisor = supervisors.get(0);  // You can change the selection logic if needed
            grievance.setSupervisor(assignedSupervisor);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Handle the case where no supervisors are available
        }

        // Set default status (for example, 'Pending')
        Status defaultStatus = statusRepository.findById(1L).orElse(null);  // Assuming '1' is the ID for 'Pending'
        grievance.setStatus(defaultStatus);

        // Save the grievance with assigned supervisor and default status
        Grievance newGrievance = grievanceRepository.save(grievance);
        return ResponseEntity.ok(newGrievance);
    }

    // Endpoint for updating grievance status
    @PutMapping("/{grievanceId}/status")
    public ResponseEntity<?> updateGrievanceStatus(@PathVariable Long grievanceId, @RequestBody StatusUpdateRequest statusUpdateRequest) {
        Long statusId = statusUpdateRequest.getStatusId();

        Optional<Grievance> grievanceOpt = grievanceRepository.findById(grievanceId);
        if (!grievanceOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grievance not found");
        }

        Optional<Status> statusOpt = statusRepository.findById(statusId);
        if (!statusOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Status not found");
        }

        Grievance grievance = grievanceOpt.get();
        grievance.setStatus(statusOpt.get());
        grievanceRepository.save(grievance);

        return ResponseEntity.ok("Status updated successfully");
    }

    @PutMapping("/{grievanceId}/assign/{assigneeId}")
    public ResponseEntity<?> assignGrievanceToAssignee(@PathVariable Long grievanceId, @PathVariable Long assigneeId) {
        Optional<Grievance> grievanceOpt = grievanceRepository.findById(grievanceId);
        if (!grievanceOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grievance not found");
        }

        Optional<Assignee> assigneeOpt = assigneeRepository.findById(assigneeId);
        if (!assigneeOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignee not found");
        }

        Grievance grievance = grievanceOpt.get();
        Assignee assignee = assigneeOpt.get();
        grievance.setAssignee(assignee);

        grievanceRepository.save(grievance);

        return ResponseEntity.ok("Grievance assigned to Assignee successfully");
    }



    // New GET endpoint to fetch all grievances
    @GetMapping("/all")
    public ResponseEntity<List<Grievance>> getAllGrievances() {
        List<Grievance> grievances = grievanceRepository.findAll();
        if (grievances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(grievances);
        }
        return ResponseEntity.ok(grievances);
    }

    @GetMapping("/supervisor/{supervisorId}/grievances")
    public ResponseEntity<List<GrievanceDTO>> getGrievancesBySupervisor(@PathVariable Long supervisorId) {
        List<Grievance> grievances = grievanceRepository.findBySupervisor_SupervisorId(supervisorId); // Ensure this method fetches grievances correctly

        List<GrievanceDTO> grievanceDTOs = grievances.stream().map(grievance -> {
            GrievanceDTO dto = new GrievanceDTO();
            dto.setGrievanceId(grievance.getGrievanceId());
            dto.setGrievanceType(grievance.getGrievanceType());
            dto.setDescription(grievance.getDescription());
            dto.setStatus(grievance.getStatus().getStatusUpdate()); // Assuming status is an entity; adjust as necessary

            UserDTO userDto = new UserDTO();
            userDto.setUserId(grievance.getUser().getUserId());
            userDto.setUserEmail(grievance.getUser().getUserEmail()); // Adjust based on your user fields
            dto.setUser(userDto);

            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(grievanceDTOs);
    }



    @GetMapping("/assignee/{assigneeId}/grievances")
    public ResponseEntity<?> getGrievancesForAssignee(@PathVariable Long assigneeId) {
        List<Grievance> grievances = grievanceRepository.findByAssignee_AssigneeId(assigneeId);

        if (grievances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No grievances assigned to this assignee");
        }
        return ResponseEntity.ok(grievances);
    }
}
