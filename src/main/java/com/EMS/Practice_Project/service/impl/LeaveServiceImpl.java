package com.EMS.Practice_Project.service.impl;

import com.EMS.Practice_Project.dto.leave.CreateLeaveRequest;
import com.EMS.Practice_Project.dto.leave.LeaveRequestDto;
import com.EMS.Practice_Project.entity.LeaveRequest;
import com.EMS.Practice_Project.exception.BadRequestException;
import com.EMS.Practice_Project.exception.ResourceNotFoundException;
import com.EMS.Practice_Project.mapper.LeaveMapper;
import com.EMS.Practice_Project.repository.LeaveRepository;
import com.EMS.Practice_Project.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveMapper leaveMapper;

    @Override
    public LeaveRequestDto applyLeave(CreateLeaveRequest request) {
        LeaveRequest leave = leaveMapper.toEntity(request);
        leave.setLeaveId("LV-" + System.currentTimeMillis());
        leave.setStatus(LeaveRequest.LeaveStatus.PENDING);
        leave = leaveRepository.save(leave);
        return leaveMapper.toDto(leave);
    }

    @Override
    public LeaveRequestDto updateLeaveStatus(String leaveId, String status) {
        LeaveRequest existing = leaveRepository.findByLeaveId(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));

        try {
            existing.setStatus(LeaveRequest.LeaveStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid leave status: " + status);
        }

        existing = leaveRepository.save(existing);
        return leaveMapper.toDto(existing);
    }

    @Override
    public List<LeaveRequestDto> getLeavesForEmployee(String employeeId) {
        return leaveRepository.findByEmployeeId(employeeId).stream()
                .map(leaveMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveRequestDto> getLeavesByStatus(String status) {
        LeaveRequest.LeaveStatus leaveStatus;
        try {
            leaveStatus = LeaveRequest.LeaveStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid leave status: " + status);
        }
        return leaveRepository.findByStatus(leaveStatus).stream()
                .map(leaveMapper::toDto)
                .collect(Collectors.toList());
    }
}
