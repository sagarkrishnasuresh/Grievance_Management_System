package com.grievance.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supervisorId;

    private String supervisorName;
    private String supervisorEmail;
    private String supervisorPassword;

    @OneToMany(mappedBy = "supervisor")
    private List<Grievance> grievances;

    // Getters and Setters
}
