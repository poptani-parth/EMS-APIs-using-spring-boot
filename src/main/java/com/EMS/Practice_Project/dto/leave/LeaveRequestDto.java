package com.EMS.Practice_Project.dto.leave;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestDto {

    private String id;
    private String leaveId;
    private String employeeId;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String reason;
}
