package com.EMS.Practice_Project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    private String id;

    private String departmentId;

    private String name;

    private String description;

    /**
     * Optionally link to the manager/HR user or employee.
     */
    private String managerId;
}