package com.hospitalManagement.Proj1.service;

import com.hospitalManagement.Proj1.model.Users;
import com.hospitalManagement.Proj1.repository.UserRepo;
import com.hospitalManagement.Proj1.security.CustomeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = userRepo.findByEmail(email);
        if(user.isEmpty())
        {
            throw new UsernameNotFoundException("User Not Found");
        }
        else {
            Users validUser=user.get();
            return new CustomeUserDetails(validUser);
        }
    }
}
