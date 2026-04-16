package com.EMS.Practice_Project.controller;

import com.EMS.Practice_Project.dto.department.CreateDepartmentRequest;
import com.EMS.Practice_Project.dto.department.DepartmentDto;
import com.EMS.Practice_Project.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody CreateDepartmentRequest request) {
        return ResponseEntity.ok(departmentService.createDepartment(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartment(
            @PathVariable String departmentId,
            @Valid @RequestBody CreateDepartmentRequest request) {
        return ResponseEntity.ok(departmentService.updateDepartment(departmentId, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable String departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> listDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
}
