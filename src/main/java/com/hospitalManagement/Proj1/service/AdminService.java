package com.hospitalManagement.Proj1.service;

import com.hospitalManagement.Proj1.model.Appointment;
import com.hospitalManagement.Proj1.model.Doctors;
import com.hospitalManagement.Proj1.model.Patients;
import com.hospitalManagement.Proj1.model.Users;
import com.hospitalManagement.Proj1.repository.AppointmentRepo;
import com.hospitalManagement.Proj1.repository.DoctorRepo;
import com.hospitalManagement.Proj1.repository.PatientRepo;
import com.hospitalManagement.Proj1.repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AppointmentRepo appointmentRepo;

    public void saveDoctor(Doctors doctor)
    {
        doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
        doctorRepo.save(doctor);
        Users user = new Users();
        user.setUsername(doctor.getUsername());
        user.setEmail(doctor.getEmail());
        user.setPassword(doctor.getPassword());
        user.setRole("ROLE_DOCTOR");
        userRepo.save(user);
    }

    public void savePatient(Patients patients)
    {
        patients.setPassword(bCryptPasswordEncoder.encode(patients.getPassword()));
        patientRepo.save(patients);
        Users user = new Users();
        user.setUsername(patients.getUsername());
        user.setEmail(patients.getEmail());
        user.setPassword(patients.getPassword());
        user.setRole("ROLE_USER");
        userRepo.save(user);
    }

    public long countTotalPatients()
    {
        return patientRepo.count();
    }
    public long countTotalDoctors()
    {
        return doctorRepo.count();
    }

    public List<Patients> findAllPatients()
    {
        return patientRepo.findAll();
    }
    public List<Doctors> findAllDoctors()
    {
        return doctorRepo.findAll();
    }
    public List<Appointment> getAllAppointments()
    {
        return appointmentRepo.findAll();
    }
    public void deleteDoctorById(Long id)
    {
        doctorRepo.deleteById(id);
        userRepo.deleteById(id);
    }
    public Doctors getDoctors(Long id)
    {
        return doctorRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("Doctor Not Found"));
    }
    public void editDoctors(Doctors doctors)
    {
        doctorRepo.save(doctors);
    }

}
