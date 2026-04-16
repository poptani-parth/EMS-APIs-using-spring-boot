package com.EMS.Practice_Project.service;

import com.EMS.Practice_Project.dto.advance.AdvancePaymentDto;
import com.EMS.Practice_Project.dto.advance.CreateAdvancePaymentRequest;

import java.util.List;

public interface AdvancePaymentService {

    AdvancePaymentDto requestAdvance(CreateAdvancePaymentRequest request);

    AdvancePaymentDto updateAdvanceStatus(String advanceId, String status);

    List<AdvancePaymentDto> getAdvanceRequestsForEmployee(String employeeId);

    List<AdvancePaymentDto> getAdvanceRequestsByStatus(String status);
}
