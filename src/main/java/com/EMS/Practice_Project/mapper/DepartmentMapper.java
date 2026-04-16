package com.EMS.Practice_Project.mapper;

import com.EMS.Practice_Project.dto.department.CreateDepartmentRequest;
import com.EMS.Practice_Project.dto.department.DepartmentDto;
import com.EMS.Practice_Project.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentDto toDto(Department entity) {
        if (entity == null) {
            return null;
        }
        DepartmentDto dto = new DepartmentDto();
        dto.setId(entity.getId());
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setManagerId(entity.getManagerId());
        return dto;
    }

    public Department toEntity(CreateDepartmentRequest request) {
        if (request == null) {
            return null;
        }
        Department entity = new Department();
        entity.setDepartmentId(request.getDepartmentId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setManagerId(request.getManagerId());
        return entity;
    }
}
