package com.EMS.Practice_Project.service;

import com.EMS.Practice_Project.dto.employee.EmployeeDto;
import com.EMS.Practice_Project.dto.leave.LeaveRequestDto;
import com.EMS.Practice_Project.dto.salary.SalaryDto;
import com.EMS.Practice_Project.dto.attendance.AttendanceDto;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public interface ReportService {

    List<EmployeeDto> getEmployeeReport();

    List<AttendanceDto> getAttendanceReport(LocalDate start, LocalDate end);

    List<SalaryDto> getSalaryReport(YearMonth month);

    List<LeaveRequestDto> getLeaveReport(String status);

    Map<String, Long> getHeadCountByDepartment();
}
