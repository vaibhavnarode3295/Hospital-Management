package com.hospitalManagement.Proj1.service;

import com.hospitalManagement.Proj1.model.Doctors;
import com.hospitalManagement.Proj1.model.Patients;
import com.hospitalManagement.Proj1.model.Users;
import com.hospitalManagement.Proj1.repository.PatientRepo;
import com.hospitalManagement.Proj1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void addUser(Users user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        Patients patients=new Patients();
        patients.setEmail(user.getEmail());
        patients.setPassword(user.getPassword());
        patients.setUsername(user.getUsername());
        patientRepo.save(patients);
    }


}
