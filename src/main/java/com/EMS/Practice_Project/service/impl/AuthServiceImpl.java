package com.EMS.Practice_Project.service.impl;

import com.EMS.Practice_Project.dto.auth.JwtResponse;
import com.EMS.Practice_Project.dto.auth.LoginRequest;
import com.EMS.Practice_Project.dto.auth.SignupRequest;
import com.EMS.Practice_Project.entity.Role;
import com.EMS.Practice_Project.entity.User;
import com.EMS.Practice_Project.exception.BadRequestException;
import com.EMS.Practice_Project.exception.ResourceNotFoundException;
import com.EMS.Practice_Project.mapper.UserMapper;
import com.EMS.Practice_Project.repository.RoleRepository;
import com.EMS.Practice_Project.repository.UserRepository;
import com.EMS.Practice_Project.security.CustomUserDetailsService;
import com.EMS.Practice_Project.security.JwtTokenProvider;
import com.EMS.Practice_Project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserMapper userMapper;

    @Override
    public JwtResponse authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Set<String> roles = user.getRoles();
        String token = tokenProvider.generateToken(authentication, roles);
        JwtResponse response = new JwtResponse(token, "Bearer", user.getId(), user.getUsername(), user.getEmail(), roles);
        return response;
    }

    @Override
    public JwtResponse register(SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username is already taken");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email is already taken");
        }

        Set<String> roleNames = new HashSet<>();
        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            roleNames.add("ROLE_EMPLOYEE");
        } else {
            roleNames.addAll(request.getRoles());
        }

        // Ensure each role exists
        for (String roleName : new HashSet<>(roleNames)) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + roleName));
            roleNames.add(role.getName());
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roleNames)
                .employeeId(request.getEmployeeId())
                .enabled(true)
                .build();

        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String token = tokenProvider.generateToken(authentication, roleNames);

        return new JwtResponse(token, "Bearer", user.getId(), user.getUsername(), user.getEmail(), roleNames);
    }
}
