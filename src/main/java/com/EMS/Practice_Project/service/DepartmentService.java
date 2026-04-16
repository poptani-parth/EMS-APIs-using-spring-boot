package com.EMS.Practice_Project.service;

import com.EMS.Practice_Project.dto.department.CreateDepartmentRequest;
import com.EMS.Practice_Project.dto.department.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto createDepartment(CreateDepartmentRequest request);

    DepartmentDto updateDepartment(String departmentId, CreateDepartmentRequest request);

    void deleteDepartment(String departmentId);

    DepartmentDto getDepartmentById(String departmentId);

    List<DepartmentDto> getAllDepartments();
}
