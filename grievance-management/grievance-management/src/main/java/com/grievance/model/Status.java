package com.grievance.model;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    private String statusUpdate;

    @OneToMany(mappedBy = "status")
    private List<Grievance> grievances;

    // Getters and Setters
}
