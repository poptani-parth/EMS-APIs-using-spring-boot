package com.EMS.Practice_Project.service.impl;

import com.EMS.Practice_Project.dto.salary.CreateSalaryRequest;
import com.EMS.Practice_Project.dto.salary.SalaryDto;
import com.EMS.Practice_Project.entity.SalaryRecord;
import com.EMS.Practice_Project.exception.ResourceNotFoundException;
import com.EMS.Practice_Project.mapper.SalaryMapper;
import com.EMS.Practice_Project.repository.SalaryRepository;
import com.EMS.Practice_Project.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public SalaryDto createSalaryRecord(CreateSalaryRequest request) {
        SalaryRecord record = salaryMapper.toEntity(request);
        record.setSalaryId("SAL-" + System.currentTimeMillis());
        record.setNetSalary(calculateNet(request.getBaseSalary(), request.getBonus(), request.getDeductions()));
        record = salaryRepository.save(record);
        return salaryMapper.toDto(record);
    }

    @Override
    public SalaryDto calculateNetSalary(String salaryId) {
        SalaryRecord record = salaryRepository.findById(salaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Salary record not found"));
        record.setNetSalary(calculateNet(record.getBaseSalary(), record.getBonus(), record.getDeductions()));
        salaryRepository.save(record);
        return salaryMapper.toDto(record);
    }

    @Override
    public List<SalaryDto> getSalaryByEmployee(String employeeId) {
        return salaryRepository.findByEmployeeId(employeeId).stream()
                .map(salaryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryDto> getSalaryByMonth(YearMonth month) {
        return salaryRepository.findByMonth(month).stream()
                .map(salaryMapper::toDto)
                .collect(Collectors.toList());
    }

    private Double calculateNet(Double base, Double bonus, Double deductions) {
        double safeBase = base == null ? 0 : base;
        double safeBonus = bonus == null ? 0 : bonus;
        double safeDeductions = deductions == null ? 0 : deductions;
        return safeBase + safeBonus - safeDeductions;
    }
}
