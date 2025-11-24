package com.hospitalManagement.Proj1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

}
