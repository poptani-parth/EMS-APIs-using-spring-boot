package com.EMS.Practice_Project.dto.employee;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class UpdateEmployeeRequest {

    @NotBlank(message = "Employee ID is required")
    private String employeeId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private String phone;

    @NotBlank(message = "Department ID is required")
    private String departmentId;

    @NotBlank(message = "Position is required")
    private String position;

    private Double salary;

    private LocalDate joiningDate;
}
