package com.EMS.Practice_Project.controller;

import com.EMS.Practice_Project.dto.advance.AdvancePaymentDto;
import com.EMS.Practice_Project.dto.advance.CreateAdvancePaymentRequest;
import com.EMS.Practice_Project.service.AdvancePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/advance-payments")
public class AdvancePaymentController {

    @Autowired
    private AdvancePaymentService advancePaymentService;

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping
    public ResponseEntity<AdvancePaymentDto> requestAdvance(@Valid @RequestBody CreateAdvancePaymentRequest request) {
        return ResponseEntity.ok(advancePaymentService.requestAdvance(request));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{advanceId}/status")
    public ResponseEntity<AdvancePaymentDto> updateAdvanceStatus(
            @PathVariable String advanceId,
            @RequestParam String status) {
        return ResponseEntity.ok(advancePaymentService.updateAdvanceStatus(advanceId, status));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AdvancePaymentDto>> getAdvanceRequestsForEmployee(@PathVariable String employeeId) {
        return ResponseEntity.ok(advancePaymentService.getAdvanceRequestsForEmployee(employeeId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/status")
    public ResponseEntity<List<AdvancePaymentDto>> getAdvanceRequestsByStatus(@RequestParam String status) {
        return ResponseEntity.ok(advancePaymentService.getAdvanceRequestsByStatus(status));
    }
}
