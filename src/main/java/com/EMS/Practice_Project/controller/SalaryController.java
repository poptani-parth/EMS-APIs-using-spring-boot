package com.EMS.Practice_Project.controller;

import com.EMS.Practice_Project.dto.salary.CreateSalaryRequest;
import com.EMS.Practice_Project.dto.salary.SalaryDto;
import com.EMS.Practice_Project.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<SalaryDto> createSalary(@Valid @RequestBody CreateSalaryRequest request) {
        return ResponseEntity.ok(salaryService.createSalaryRecord(request));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/{salaryId}/calculate")
    public ResponseEntity<SalaryDto> calculateNetSalary(@PathVariable String salaryId) {
        return ResponseEntity.ok(salaryService.calculateNetSalary(salaryId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<SalaryDto>> getSalaryByEmployee(@PathVariable String employeeId) {
        return ResponseEntity.ok(salaryService.getSalaryByEmployee(employeeId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/month")
    public ResponseEntity<List<SalaryDto>> getSalaryByMonth(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        return ResponseEntity.ok(salaryService.getSalaryByMonth(month));
    }
}
