package com.EMS.Practice_Project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    private String id;

    private String attendanceId;

    private String employeeId;

    private LocalDate date;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private AttendanceStatus status;

    public enum AttendanceStatus {
        PRESENT,
        ABSENT,
        ON_LEAVE,
        HALF_DAY
    }
}
