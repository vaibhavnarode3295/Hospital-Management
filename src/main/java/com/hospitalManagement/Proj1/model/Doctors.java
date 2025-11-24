package com.hospitalManagement.Proj1.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Doctors")
@SQLDelete(sql="UPDATE DOCTORS SET RECORD_STATUS='deleted' WHERE ID=?")
@SQLRestriction("RECORD_STATUS <> 'deleted'")
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String specialization;
    private String password;
    private String status;

    private String recordStatus="active";
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();
}
