package com.hospitalManagement.Proj1.repository;

import com.hospitalManagement.Proj1.model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DoctorRepo extends JpaRepository<Doctors,Long> {
//    @Query("SELECT d FROM Doctor d WHERE d.specialization = :spec AND d.status = :status")
//    List<Doctors> findBySpecificationAndStatus(@Param("spec") String specialization,@Param("status") String status);
    List<Doctors> findBySpecializationAndStatus(String specialization, String status);

    Doctors findByEmail(String username);
}
