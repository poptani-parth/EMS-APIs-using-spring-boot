package com.EMS.Practice_Project.mapper;

import com.EMS.Practice_Project.dto.advance.AdvancePaymentDto;
import com.EMS.Practice_Project.dto.advance.CreateAdvancePaymentRequest;
import com.EMS.Practice_Project.entity.AdvancePayment;
import org.springframework.stereotype.Component;

@Component
public class AdvancePaymentMapper {

    public AdvancePaymentDto toDto(AdvancePayment entity) {
        if (entity == null) {
            return null;
        }
        AdvancePaymentDto dto = new AdvancePaymentDto();
        dto.setId(entity.getId());
        dto.setAdvanceId(entity.getAdvanceId());
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setAmount(entity.getAmount());
        dto.setRequestedDate(entity.getRequestedDate());
        dto.setApprovedDate(entity.getApprovedDate());
        dto.setStatus(entity.getStatus() != null ? entity.getStatus().name() : null);
        dto.setRemarks(entity.getRemarks());
        return dto;
    }

    public AdvancePayment toEntity(CreateAdvancePaymentRequest request) {
        if (request == null) {
            return null;
        }
        AdvancePayment entity = new AdvancePayment();
        entity.setEmployeeId(request.getEmployeeId());
        entity.setAmount(request.getAmount());
        entity.setRequestedDate(request.getRequestedDate());
        entity.setRemarks(request.getRemarks());
        return entity;
    }
}
