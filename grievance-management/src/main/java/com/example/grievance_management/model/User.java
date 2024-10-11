package com.example.grievance_management.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Entity
@Data
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @JsonProperty("Name")
    private String userName;

    @JsonProperty("Email")
    private String userEmail;

    @JsonProperty("Password")
    private String userPassword;

    @Getter
    @JsonProperty("role")
    private final String role = "user";  // Fixed role as "user"


    @OneToMany(mappedBy = "user")
    private List<Grievance> grievances;

}
