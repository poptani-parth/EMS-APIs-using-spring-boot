package com.EMS.Practice_Project.service;

import com.EMS.Practice_Project.dto.employee.CreateEmployeeRequest;
import com.EMS.Practice_Project.dto.employee.EmployeeDto;
import com.EMS.Practice_Project.dto.employee.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(CreateEmployeeRequest request);

    EmployeeDto updateEmployee(String employeeId, UpdateEmployeeRequest request);

    void deleteEmployee(String employeeId);

    EmployeeDto getEmployeeById(String employeeId);

    List<EmployeeDto> searchEmployees(String query);

    List<EmployeeDto> getAllEmployees();
}
