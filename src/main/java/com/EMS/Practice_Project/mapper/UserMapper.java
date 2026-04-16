package com.EMS.Practice_Project.mapper;

import com.EMS.Practice_Project.dto.auth.JwtResponse;
import com.EMS.Practice_Project.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserMapper {

    public JwtResponse toJwtResponse(User user, String token, Set<String> roles) {
        if (user == null) {
            return null;
        }
        JwtResponse response = new JwtResponse(token, "Bearer", user.getId(), user.getUsername(), user.getEmail(), roles);
        return response;
    }
}
