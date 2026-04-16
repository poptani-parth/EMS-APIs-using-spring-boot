package com.EMS.Practice_Project.service.impl;

import com.EMS.Practice_Project.dto.employee.CreateEmployeeRequest;
import com.EMS.Practice_Project.dto.employee.EmployeeDto;
import com.EMS.Practice_Project.dto.employee.UpdateEmployeeRequest;
import com.EMS.Practice_Project.entity.Employee;
import com.EMS.Practice_Project.exception.BadRequestException;
import com.EMS.Practice_Project.exception.ResourceNotFoundException;
import com.EMS.Practice_Project.mapper.EmployeeMapper;
import com.EMS.Practice_Project.repository.EmployeeRepository;
import com.EMS.Practice_Project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto createEmployee(CreateEmployeeRequest request) {
        if (employeeRepository.existsByEmployeeId(request.getEmployeeId())) {
            throw new BadRequestException("Employee with ID already exists");
        }
        Employee employee = employeeMapper.toEntity(request);
        employee = employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(String employeeId, UpdateEmployeeRequest request) {
        Employee existing = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeMapper.updateEntityFromDto(request, existing);
        Employee updated = employeeRepository.save(existing);
        return employeeMapper.toDto(updated);
    }

    @Override
    public void deleteEmployee(String employeeId) {
        Employee existing = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeRepository.delete(existing);
    }

    @Override
    public EmployeeDto getEmployeeById(String employeeId) {
        return employeeRepository.findByEmployeeId(employeeId)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<EmployeeDto> searchEmployees(String query) {
        // Basic search by name or email
        return employeeRepository.findAll().stream()
                .filter(emp -> emp.getName().toLowerCase().contains(query.toLowerCase())
                        || emp.getEmail().toLowerCase().contains(query.toLowerCase())
                        || emp.getEmployeeId().toLowerCase().contains(query.toLowerCase()))
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }
}
