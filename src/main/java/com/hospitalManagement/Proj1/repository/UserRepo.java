package com.hospitalManagement.Proj1.repository;

import com.hospitalManagement.Proj1.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
}
