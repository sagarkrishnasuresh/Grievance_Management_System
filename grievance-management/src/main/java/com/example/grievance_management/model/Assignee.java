package com.example.grievance_management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "assignee")
public class Assignee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assigneeId;

    @JsonProperty("Name")
    private String assigneeName;

    @JsonProperty("Email")
    private String assigneeEmail;

    @JsonProperty("Password")
    private String assigneePassword;

    @JsonProperty("role")
    private final String role = "assignee";  // Fixed role as "assignee"

    @OneToMany(mappedBy = "assignee")
    private List<Grievance> grievances;
}
