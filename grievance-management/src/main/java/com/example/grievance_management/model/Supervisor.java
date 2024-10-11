package com.example.grievance_management.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "supervisor")
public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supervisorId;

    @JsonProperty("Name")
    private String supervisorName;

    @JsonProperty("Email")
    private String supervisorEmail;

    @JsonProperty("Password")
    private String supervisorPassword;

    @JsonProperty("role")
    private final String role = "supervisor";  // Fixed role as "supervisor"


    @OneToMany(mappedBy = "supervisor")
    private List<Grievance> grievances;
}
