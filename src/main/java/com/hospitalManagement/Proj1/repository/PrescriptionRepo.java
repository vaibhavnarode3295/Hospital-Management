package com.hospitalManagement.Proj1.repository;

import com.hospitalManagement.Proj1.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepo extends JpaRepository<Prescription,Long> {

    List<Prescription> findByPatientIdOrderByDateIssuedDesc(Long id);
}
