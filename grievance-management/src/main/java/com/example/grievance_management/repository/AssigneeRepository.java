package com.example.grievance_management.repository;

import com.example.grievance_management.model.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssigneeRepository extends JpaRepository<Assignee, Long> {
    // Custom method to find assignee by email
    Assignee findByAssigneeEmail(String assigneeEmail);
}
