package com.EMS.Practice_Project.dto.advance;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CreateAdvancePaymentRequest {

    @NotBlank(message = "Employee ID is required")
    private String employeeId;

    @NotNull(message = "Amount is required")
    private Double amount;

    private LocalDate requestedDate;

    private String remarks;
}
