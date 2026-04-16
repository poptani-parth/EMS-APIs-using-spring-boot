package com.EMS.Practice_Project.dto.advance;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdvancePaymentDto {

    private String id;
    private String advanceId;
    private String employeeId;
    private Double amount;
    private LocalDate requestedDate;
    private LocalDate approvedDate;
    private String status;
    private String remarks;
}
