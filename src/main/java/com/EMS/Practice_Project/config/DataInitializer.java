package com.EMS.Practice_Project.config;

import com.EMS.Practice_Project.entity.Role;
import com.EMS.Practice_Project.entity.User;
import com.EMS.Practice_Project.repository.RoleRepository;
import com.EMS.Practice_Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        createRoleIfNotExists("ROLE_ADMIN");
        createRoleIfNotExists("ROLE_MANAGER");
        createRoleIfNotExists("ROLE_EMPLOYEE");

        if (!userRepository.existsByUsername("admin")) {
            Set<String> roles = new HashSet<>();
            roles.add("ROLE_ADMIN");
            User admin = User.builder()
                    .username("admin")
                    .email("admin@ems.local")
                    .password(passwordEncoder.encode("Admin123!"))
                    .roles(roles)
                    .enabled(true)
                    .build();
            userRepository.save(admin);
        }
    }

    private void createRoleIfNotExists(String roleName) {
        if (roleRepository.findByName(roleName).isEmpty()) {
            roleRepository.save(Role.builder().name(roleName).build());
        }
    }
}
