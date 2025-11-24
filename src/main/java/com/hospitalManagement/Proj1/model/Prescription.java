package com.hospitalManagement.Proj1.model;

import com.hospitalManagement.Proj1.model.Appointment;
import com.hospitalManagement.Proj1.model.Doctors;
import com.hospitalManagement.Proj1.model.Patients;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
@Data
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String medicines;
    @Column(length = 2000)
    private String advice;
    @Column(length = 2000)
    private String tests;
    private LocalDate followUpDate;
    private LocalDate dateIssued;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patients patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctors doctor;
    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
}

