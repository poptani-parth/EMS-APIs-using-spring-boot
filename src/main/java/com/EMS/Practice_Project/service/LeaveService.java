package com.EMS.Practice_Project.service;

import com.EMS.Practice_Project.dto.leave.CreateLeaveRequest;
import com.EMS.Practice_Project.dto.leave.LeaveRequestDto;

import java.util.List;

public interface LeaveService {

    LeaveRequestDto applyLeave(CreateLeaveRequest request);

    LeaveRequestDto updateLeaveStatus(String leaveId, String status);

    List<LeaveRequestDto> getLeavesForEmployee(String employeeId);

    List<LeaveRequestDto> getLeavesByStatus(String status);
}
