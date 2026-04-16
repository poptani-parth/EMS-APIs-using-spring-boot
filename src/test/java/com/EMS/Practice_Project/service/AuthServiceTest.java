package com.EMS.Practice_Project.service;

import com.EMS.Practice_Project.dto.auth.LoginRequest;
import com.EMS.Practice_Project.dto.auth.SignupRequest;
import com.EMS.Practice_Project.entity.Role;
import com.EMS.Practice_Project.entity.User;
import com.EMS.Practice_Project.repository.RoleRepository;
import com.EMS.Practice_Project.repository.UserRepository;
import com.EMS.Practice_Project.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JwtTokenProvider tokenProvider;

    @Spy
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    private com.EMS.Practice_Project.service.impl.AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signupShouldThrowWhenUsernameExists() {
        SignupRequest request = new SignupRequest();
        request.setUsername("existing");
        request.setEmail("new@example.com");
        request.setPassword("password");

        when(userRepository.existsByUsername("existing")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> authService.register(request));
    }

    @Test
    void signupShouldThrowWhenEmailExists() {
        SignupRequest request = new SignupRequest();
        request.setUsername("newuser");
        request.setEmail("existing@example.com");
        request.setPassword("password");

        when(userRepository.existsByUsername("newuser")).thenReturn(false);
        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> authService.register(request));
    }

    @Test
    void loginShouldThrowWhenUserNotFound() {
        LoginRequest request = new LoginRequest();
        request.setUsername("nouser");
        request.setPassword("pwd");

        when(userRepository.findByUsername("nouser")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authService.authenticate(request));
    }
}
