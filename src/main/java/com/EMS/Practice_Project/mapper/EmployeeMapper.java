package com.EMS.Practice_Project.mapper;

import com.EMS.Practice_Project.dto.employee.CreateEmployeeRequest;
import com.EMS.Practice_Project.dto.employee.EmployeeDto;
import com.EMS.Practice_Project.dto.employee.UpdateEmployeeRequest;
import com.EMS.Practice_Project.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeMapper {

    public EmployeeDto toDto(Employee entity) {
        if (entity == null) {
            return null;
        }
        EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setPosition(entity.getPosition());
        dto.setSalary(entity.getSalary());
        dto.setJoiningDate(entity.getJoiningDate());
        return dto;
    }

    public Employee toEntity(CreateEmployeeRequest request) {
        if (request == null) {
            return null;
        }
        Employee entity = new Employee();
        entity.setEmployeeId(request.getEmployeeId());
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setPosition(request.getPosition());
        entity.setSalary(request.getSalary());
        entity.setJoiningDate(request.getJoiningDate());
        return entity;
    }

    public void updateEntityFromDto(UpdateEmployeeRequest request, Employee entity) {
        if (request == null || entity == null) {
            return;
        }
        entity.setEmployeeId(request.getEmployeeId());
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setPosition(request.getPosition());
        entity.setSalary(request.getSalary());
        entity.setJoiningDate(request.getJoiningDate());
    }
}
