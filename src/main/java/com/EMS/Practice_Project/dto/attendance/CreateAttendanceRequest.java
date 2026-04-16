package com.EMS.Practice_Project.dto.attendance;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateAttendanceRequest {

    @NotBlank(message = "Employee ID is required")
    private String employeeId;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    @NotBlank(message = "Status is required")
    private String status;
}
