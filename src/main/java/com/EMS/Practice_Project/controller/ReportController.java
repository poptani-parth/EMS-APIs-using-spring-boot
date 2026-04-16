package com.EMS.Practice_Project.controller;

import com.EMS.Practice_Project.dto.attendance.AttendanceDto;
import com.EMS.Practice_Project.dto.employee.EmployeeDto;
import com.EMS.Practice_Project.dto.leave.LeaveRequestDto;
import com.EMS.Practice_Project.dto.salary.SalaryDto;
import com.EMS.Practice_Project.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> employeeReport() {
        return ResponseEntity.ok(reportService.getEmployeeReport());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/attendance")
    public ResponseEntity<List<AttendanceDto>> attendanceReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(reportService.getAttendanceReport(start, end));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/salaries")
    public ResponseEntity<List<SalaryDto>> salaryReport(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        return ResponseEntity.ok(reportService.getSalaryReport(month));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/leaves")
    public ResponseEntity<List<LeaveRequestDto>> leaveReport(@RequestParam String status) {
        return ResponseEntity.ok(reportService.getLeaveReport(status));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/headcount")
    public ResponseEntity<Map<String, Long>> headcountByDepartment() {
        return ResponseEntity.ok(reportService.getHeadCountByDepartment());
    }
}
