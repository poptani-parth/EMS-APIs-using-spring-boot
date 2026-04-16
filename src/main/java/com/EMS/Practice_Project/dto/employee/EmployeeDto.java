package com.EMS.Practice_Project.dto.employee;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {

    private String id;

    private String employeeId;

    private String name;

    private String email;

    private String phone;

    private String departmentId;

    private String position;

    private Double salary;

    private LocalDate joiningDate;
}
