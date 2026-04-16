package com.EMS.Practice_Project.dto.attendance;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AttendanceDto {

    private String id;
    private String attendanceId;
    private String employeeId;
    private LocalDate date;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String status;
}
