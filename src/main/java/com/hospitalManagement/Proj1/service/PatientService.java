package com.hospitalManagement.Proj1.service;

import com.hospitalManagement.Proj1.model.Appointment;
import com.hospitalManagement.Proj1.model.Doctors;
import com.hospitalManagement.Proj1.model.Patients;
import com.hospitalManagement.Proj1.repository.DoctorRepo;
import com.hospitalManagement.Proj1.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private AppointmentService appointmentService;

    public Patients findPatinet(String username)
    {
        Patients patients= patientRepo.findByEmail(username);
        if (patients==null) {
            throw new IllegalArgumentException("Patient Not Found");
        }
        else {
            return patients;
        }
    }

    public Doctors findDoctorById(Long id)
    {
        return doctorRepo.findById(id).orElse(null);
    }

    public List<Doctors> findAllDoctors()
    {
        return doctorRepo.findAll();
    }

    public Patients findPatientById(Long id)
    {
        return patientRepo.findById(id).orElseThrow();
    }

    public List<Appointment> todayAppointment(String username){
        Patients patients=findPatinet(username);
        System.out.println("Patient name is "+patients.getUsername()+" and id is: "+patients.getId());
        List<Appointment> appointmentList= appointmentService.findAppointmentSchedule(patients.getId());
        return appointmentList;
    }

}
