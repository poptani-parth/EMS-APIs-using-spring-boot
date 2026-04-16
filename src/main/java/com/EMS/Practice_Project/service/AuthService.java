package com.EMS.Practice_Project.service;

import com.EMS.Practice_Project.dto.auth.JwtResponse;
import com.EMS.Practice_Project.dto.auth.LoginRequest;
import com.EMS.Practice_Project.dto.auth.SignupRequest;

public interface AuthService {

    JwtResponse authenticate(LoginRequest request);

    JwtResponse register(SignupRequest request);
}
