package com.EMS.Practice_Project.dto.salary;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.YearMonth;

@Data
public class CreateSalaryRequest {

    @NotBlank(message = "Employee ID is required")
    private String employeeId;

    @NotNull(message = "Base salary is required")
    private Double baseSalary;

    private Double bonus = 0.0;

    private Double deductions = 0.0;

    @NotNull(message = "Month is required")
    private YearMonth month;

    private String remarks;
}
