package com.EMS.Practice_Project.mapper;

import com.EMS.Practice_Project.dto.salary.CreateSalaryRequest;
import com.EMS.Practice_Project.dto.salary.SalaryDto;
import com.EMS.Practice_Project.entity.SalaryRecord;
import org.springframework.stereotype.Component;

@Component
public class SalaryMapper {

    public SalaryDto toDto(SalaryRecord entity) {
        if (entity == null) {
            return null;
        }
        SalaryDto dto = new SalaryDto();
        dto.setId(entity.getId());
        dto.setSalaryId(entity.getSalaryId());
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setBaseSalary(entity.getBaseSalary());
        dto.setBonus(entity.getBonus());
        dto.setDeductions(entity.getDeductions());
        dto.setNetSalary(entity.getNetSalary());
        dto.setMonth(entity.getMonth());
        dto.setRemarks(entity.getRemarks());
        return dto;
    }

    public SalaryRecord toEntity(CreateSalaryRequest request) {
        if (request == null) {
            return null;
        }
        SalaryRecord entity = new SalaryRecord();
        entity.setEmployeeId(request.getEmployeeId());
        entity.setBaseSalary(request.getBaseSalary());
        entity.setBonus(request.getBonus());
        entity.setDeductions(request.getDeductions());
        entity.setMonth(request.getMonth());
        entity.setRemarks(request.getRemarks());
        return entity;
    }
}
