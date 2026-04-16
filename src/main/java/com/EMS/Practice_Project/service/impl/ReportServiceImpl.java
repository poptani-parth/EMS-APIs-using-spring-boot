package com.EMS.Practice_Project.service.impl;

import com.EMS.Practice_Project.dto.attendance.AttendanceDto;
import com.EMS.Practice_Project.dto.employee.EmployeeDto;
import com.EMS.Practice_Project.dto.leave.LeaveRequestDto;
import com.EMS.Practice_Project.dto.salary.SalaryDto;
import com.EMS.Practice_Project.entity.Employee;
import com.EMS.Practice_Project.mapper.AttendanceMapper;
import com.EMS.Practice_Project.mapper.EmployeeMapper;
import com.EMS.Practice_Project.mapper.LeaveMapper;
import com.EMS.Practice_Project.mapper.SalaryMapper;
import com.EMS.Practice_Project.repository.AttendanceRepository;
import com.EMS.Practice_Project.repository.EmployeeRepository;
import com.EMS.Practice_Project.repository.LeaveRepository;
import com.EMS.Practice_Project.repository.SalaryRepository;
import com.EMS.Practice_Project.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private SalaryMapper salaryMapper;

    @Autowired
    private LeaveMapper leaveMapper;

    @Override
    public List<EmployeeDto> getEmployeeReport() {
        return employeeRepository.findAll().stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDto> getAttendanceReport(LocalDate start, LocalDate end) {
        // Basic implementation: get all attendance within date range.
        return attendanceRepository.findByDate(start).stream()
                .filter(a -> !a.getDate().isBefore(start) && !a.getDate().isAfter(end))
                .map(attendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryDto> getSalaryReport(YearMonth month) {
        return salaryRepository.findByMonth(month).stream().map(salaryMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<LeaveRequestDto> getLeaveReport(String status) {
        return leaveRepository.findByStatus(
                com.EMS.Practice_Project.entity.LeaveRequest.LeaveStatus.valueOf(status.toUpperCase()))
                .stream()
                .map(leaveMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> getHeadCountByDepartment() {
        return employeeRepository.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId, Collectors.counting()));
    }
}

