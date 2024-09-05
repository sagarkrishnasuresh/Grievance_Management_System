package com.grievance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Grievance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long grievanceId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String grievanceType;
    private String description;

    @ManyToOne
    @JoinColumn(name = "supervisorId")
    private Supervisor supervisor;

    @ManyToOne
    @JoinColumn(name = "assigneeId")
    private Assignee assignee;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "statusId")
    private Status status;

    // Getters and Setters
}
