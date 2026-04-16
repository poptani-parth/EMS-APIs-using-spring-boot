package com.EMS.Practice_Project.dto.department;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class CreateDepartmentRequest {

    @NotBlank(message = "Department ID is required")
    private String departmentId;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    private String managerId;
}
