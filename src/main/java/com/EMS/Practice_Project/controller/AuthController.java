package com.EMS.Practice_Project.controller;

import com.EMS.Practice_Project.dto.auth.JwtResponse;
import com.EMS.Practice_Project.dto.auth.LoginRequest;
import com.EMS.Practice_Project.dto.auth.SignupRequest;
import com.EMS.Practice_Project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtResponse> signup(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
