package com.EMS.Practice_Project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "leaves")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {

    @Id
    private String id;

    private String leaveId;

    private String employeeId;

    private LeaveType leaveType;

    private LocalDate startDate;

    private LocalDate endDate;

    private LeaveStatus status;

    private String reason;

    public enum LeaveType {
        SICK,
        CASUAL,
        EARNED,
        OTHER
    }

    public enum LeaveStatus {
        PENDING,
        APPROVED,
        REJECTED,
        CANCELLED
    }
}
