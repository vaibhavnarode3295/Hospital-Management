package com.hospitalManagement.Proj1.repository;

import com.hospitalManagement.Proj1.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.doctor.id = :doctorId AND a.status = 'Schedule'")
    Long countPatientsByDoctorId(Long doctorId);

    List<Appointment> findAppointmentByPatientId(Long id);

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.doctor.id = :doctorId AND a.status = 'Schedule' AND a.appointmentDate = :localDate")
    Long countTodaysPatientsByDoctorId(Long doctorId,LocalDate localDate);
    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.doctor.id = :doctorId AND a.status = 'Treted'")
    Long countPatientsTreatedByDoctorId(Long doctorId);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND a.status = 'Schedule' AND a.appointmentDate = :localDate")
    List<Appointment> getAllSchedulePatientsByDoctorId(Long doctorId, LocalDate localDate);

    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate = :today AND a.status = 'Schedule' AND a.doctor.id = :doctorId ORDER BY  a.appointmentTime ASC")
    List<Appointment> findTodayAppointmentsForDoctor(@Param("today") LocalDate today,
            @Param("doctorId") Long doctorId);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND a.status = 'Treted'")
    List<Appointment> getAllTreatedPatientsByDoctorId(Long doctorId);
}
