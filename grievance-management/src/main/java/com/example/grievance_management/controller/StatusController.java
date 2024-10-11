package com.example.grievance_management.controller;

import com.example.grievance_management.model.Status;
import com.example.grievance_management.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;

    @GetMapping
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Status> addStatus(@RequestBody Status status) {
        Status newStatus = statusRepository.save(status);
        return ResponseEntity.ok(newStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable Long id) {
        return statusRepository.findById(id)
                .map(status -> ResponseEntity.ok(status))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id) {
        statusRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
