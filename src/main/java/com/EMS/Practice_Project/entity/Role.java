package com.EMS.Practice_Project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    private String id;

    /**
     * Example values: ROLE_ADMIN, ROLE_MANAGER, ROLE_EMPLOYEE
     */
    private String name;
}
