package com.EMS.Practice_Project.service.impl;

import com.EMS.Practice_Project.dto.department.CreateDepartmentRequest;
import com.EMS.Practice_Project.dto.department.DepartmentDto;
import com.EMS.Practice_Project.entity.Department;
import com.EMS.Practice_Project.exception.BadRequestException;
import com.EMS.Practice_Project.exception.ResourceNotFoundException;
import com.EMS.Practice_Project.mapper.DepartmentMapper;
import com.EMS.Practice_Project.repository.DepartmentRepository;
import com.EMS.Practice_Project.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public DepartmentDto createDepartment(CreateDepartmentRequest request) {
        if (departmentRepository.existsByDepartmentId(request.getDepartmentId())) {
            throw new BadRequestException("Department already exists");
        }
        Department department = departmentMapper.toEntity(request);
        department = departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(String departmentId, CreateDepartmentRequest request) {
        Department existing = departmentRepository.findByDepartmentId(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setManagerId(request.getManagerId());
        existing = departmentRepository.save(existing);
        return departmentMapper.toDto(existing);
    }

    @Override
    public void deleteDepartment(String departmentId) {
        Department existing = departmentRepository.findByDepartmentId(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        departmentRepository.delete(existing);
    }

    @Override
    public DepartmentDto getDepartmentById(String departmentId) {
        return departmentRepository.findByDepartmentId(departmentId)
                .map(departmentMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }
}
