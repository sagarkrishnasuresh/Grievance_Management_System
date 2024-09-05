package com.grievance.controller;

import com.grievance.model.Grievance;
import com.grievance.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grievances")
public class GrievanceController {

    @Autowired
    private GrievanceService grievanceService;

    @PostMapping("/create")
    public ResponseEntity<Grievance> createGrievance(@RequestBody Grievance grievance) {
        Grievance createdGrievance = grievanceService.createGrievance(grievance);
        return ResponseEntity.ok(createdGrievance);
    }

    @PutMapping("/update")
    public ResponseEntity<Grievance> updateGrievance(@RequestBody Grievance grievance) {
        Grievance updatedGrievance = grievanceService.updateGrievance(grievance);
        return ResponseEntity.ok(updatedGrievance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grievance> getGrievanceById(@PathVariable Long id) {
        Grievance grievance = grievanceService.getGrievanceById(id);
        return ResponseEntity.ok(grievance);
    }
}
