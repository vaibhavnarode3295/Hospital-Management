package com.hospitalManagement.Proj1.service;

import com.hospitalManagement.Proj1.model.Appointment;
import com.hospitalManagement.Proj1.model.Doctors;
import com.hospitalManagement.Proj1.model.Patients;
import com.hospitalManagement.Proj1.repository.AppointmentRepo;
import com.hospitalManagement.Proj1.repository.DoctorRepo;
import com.hospitalManagement.Proj1.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private PatientRepo patientRepo;
    public Doctors findRecord(String username)
    {
        return doctorRepo.findByEmail(username);
    }
    public Long countTotalPatientsTretead(Long id)
    {
        return appointmentRepo.countPatientsTreatedByDoctorId(id);
    }
     public Long countTodaysPatients(Long id)
     {
         LocalDate localDate=LocalDate.now();
         return appointmentRepo.countTodaysPatientsByDoctorId(id,localDate);
     }
     public List<Appointment> getAllSchedulePatients(Long id)
     {
         LocalDate localDate=LocalDate.now();
         return appointmentRepo.getAllSchedulePatientsByDoctorId(id,localDate);
     }

     public Doctors getDoctors(String email)
     {
         return doctorRepo.findByEmail(email);
     }

     public List<Appointment> findAllPatients(Long id)
     {
         List<Appointment> list=appointmentRepo.getAllTreatedPatientsByDoctorId(id);
         return list;
     }

     public void setDoctorStatus(Long id, String status)
     {
         Doctors doctors=doctorRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Doctor Not Found"));
         doctors.setStatus(status);
         doctorRepo.save(doctors);
     }
}
