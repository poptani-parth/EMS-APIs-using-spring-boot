package com.EMS.Practice_Project.controller;

import com.EMS.Practice_Project.dto.leave.CreateLeaveRequest;
import com.EMS.Practice_Project.dto.leave.LeaveRequestDto;
import com.EMS.Practice_Project.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping
    public ResponseEntity<LeaveRequestDto> applyLeave(@Valid @RequestBody CreateLeaveRequest request) {
        return ResponseEntity.ok(leaveService.applyLeave(request));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{leaveId}/status")
    public ResponseEntity<LeaveRequestDto> updateLeaveStatus(
            @PathVariable String leaveId,
            @RequestParam String status) {
        return ResponseEntity.ok(leaveService.updateLeaveStatus(leaveId, status));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequestDto>> getLeavesForEmployee(@PathVariable String employeeId) {
        return ResponseEntity.ok(leaveService.getLeavesForEmployee(employeeId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/status")
    public ResponseEntity<List<LeaveRequestDto>> getLeavesByStatus(@RequestParam String status) {
        return ResponseEntity.ok(leaveService.getLeavesByStatus(status));
    }
}
