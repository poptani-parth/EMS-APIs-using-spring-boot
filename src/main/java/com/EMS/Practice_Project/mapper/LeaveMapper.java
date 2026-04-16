package com.EMS.Practice_Project.mapper;

import com.EMS.Practice_Project.dto.leave.CreateLeaveRequest;
import com.EMS.Practice_Project.dto.leave.LeaveRequestDto;
import com.EMS.Practice_Project.entity.LeaveRequest;
import org.springframework.stereotype.Component;

@Component
public class LeaveMapper {

    public LeaveRequestDto toDto(LeaveRequest entity) {
        if (entity == null) {
            return null;
        }
        LeaveRequestDto dto = new LeaveRequestDto();
        dto.setId(entity.getId());
        dto.setLeaveId(entity.getLeaveId());
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setLeaveType(entity.getLeaveType() != null ? entity.getLeaveType().name() : null);
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setStatus(entity.getStatus() != null ? entity.getStatus().name() : null);
        dto.setReason(entity.getReason());
        return dto;
    }

    public LeaveRequest toEntity(CreateLeaveRequest request) {
        if (request == null) {
            return null;
        }
        LeaveRequest entity = new LeaveRequest();
        entity.setEmployeeId(request.getEmployeeId());
        if (request.getLeaveType() != null) {
            try {
                entity.setLeaveType(LeaveRequest.LeaveType.valueOf(request.getLeaveType().toUpperCase()));
            } catch (IllegalArgumentException e) {
                entity.setLeaveType(LeaveRequest.LeaveType.OTHER);
            }
        }
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setReason(request.getReason());
        return entity;
    }
}
