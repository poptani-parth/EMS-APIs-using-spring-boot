package com.EMS.Practice_Project.dto.salary;

import lombok.Data;

import java.time.YearMonth;

@Data
public class SalaryDto {

    private String id;
    private String salaryId;
    private String employeeId;
    private Double baseSalary;
    private Double bonus;
    private Double deductions;
    private Double netSalary;
    private YearMonth month;
    private String remarks;
}
