package com.hospitalManagement.Proj1.service;

import com.hospitalManagement.Proj1.model.Doctors;
import com.hospitalManagement.Proj1.model.LoginVO;
import com.hospitalManagement.Proj1.model.Patients;
import com.hospitalManagement.Proj1.model.Users;
import com.hospitalManagement.Proj1.repository.PatientRepo;
import com.hospitalManagement.Proj1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public void addUser(Users user)
    {
        Optional<Users> newUser= userRepo.findByEmail(user.getEmail());
        if(newUser.isPresent()){
            throw new IllegalArgumentException("User is already registered");
        }
        Users saveUser = new Users();
        saveUser.setRole("ROLE_USER");
        saveUser.setEmail(user.getEmail());
        saveUser.setUsername(user.getUsername());
        saveUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(saveUser);
        Patients patients=new Patients();
        patients.setEmail(user.getEmail());
        patients.setPassword(user.getPassword());
        patients.setUsername(user.getUsername());
        patientRepo.save(patients);
    }



}
