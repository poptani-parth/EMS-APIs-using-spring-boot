package com.EMS.Practice_Project.controller;

import com.EMS.Practice_Project.dto.employee.CreateEmployeeRequest;
import com.EMS.Practice_Project.dto.employee.EmployeeDto;
import com.EMS.Practice_Project.dto.employee.UpdateEmployeeRequest;
import com.EMS.Practice_Project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        return ResponseEntity.ok(employeeService.createEmployee(request));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable String employeeId,
            @Valid @RequestBody UpdateEmployeeRequest request) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable String employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> listEmployees(@RequestParam(required = false) String search) {
        if (search == null || search.isBlank()) {
            return ResponseEntity.ok(employeeService.getAllEmployees());
        }
        return ResponseEntity.ok(employeeService.searchEmployees(search));
    }
}
