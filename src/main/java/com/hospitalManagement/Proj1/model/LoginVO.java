package com.hospitalManagement.Proj1.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginVO {
    private String email;
    private String password;
}
