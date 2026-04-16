package com.EMS.Practice_Project.controller;

import com.EMS.Practice_Project.dto.attendance.AttendanceDto;
import com.EMS.Practice_Project.dto.attendance.CreateAttendanceRequest;
import com.EMS.Practice_Project.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
    @PostMapping
    public ResponseEntity<AttendanceDto> recordAttendance(@Valid @RequestBody CreateAttendanceRequest request) {
        return ResponseEntity.ok(attendanceService.recordAttendance(request));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AttendanceDto>> getAttendanceForEmployee(
            @PathVariable String employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(attendanceService.getAttendanceForEmployee(employeeId, start, end));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/date")
    public ResponseEntity<List<AttendanceDto>> getAttendanceByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(attendanceService.getAttendanceByDate(date));
    }
}
