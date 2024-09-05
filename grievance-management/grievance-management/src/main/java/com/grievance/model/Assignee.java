package com.grievance.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Assignee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assigneeId;

    private String assigneeName;
    private String assigneeEmail;
    private String assigneePassword;

    @OneToMany(mappedBy = "assignee")
    private List<Grievance> grievances;

    // Getters and Setters
}
