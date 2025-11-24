package com.hospitalManagement.Proj1.repository;

import com.hospitalManagement.Proj1.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patients,Long> {
    Patients findByUsername(String username);

    Patients findByEmail(String username);
}
