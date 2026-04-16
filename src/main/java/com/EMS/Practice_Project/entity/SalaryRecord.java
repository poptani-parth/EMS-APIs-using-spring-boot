package com.EMS.Practice_Project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.YearMonth;

@Document(collection = "salary")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryRecord {

    @Id
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
