package com.EMS.Practice_Project.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private Set<String> roles;
}
