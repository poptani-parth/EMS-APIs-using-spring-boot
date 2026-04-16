package com.EMS.Practice_Project.service;

import com.EMS.Practice_Project.dto.attendance.AttendanceDto;
import com.EMS.Practice_Project.dto.attendance.CreateAttendanceRequest;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    AttendanceDto recordAttendance(CreateAttendanceRequest request);

    List<AttendanceDto> getAttendanceForEmployee(String employeeId, LocalDate start, LocalDate end);

    List<AttendanceDto> getAttendanceByDate(LocalDate date);
}
