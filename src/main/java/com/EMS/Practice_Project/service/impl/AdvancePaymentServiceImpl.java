package com.EMS.Practice_Project.service.impl;

import com.EMS.Practice_Project.dto.advance.AdvancePaymentDto;
import com.EMS.Practice_Project.dto.advance.CreateAdvancePaymentRequest;
import com.EMS.Practice_Project.entity.AdvancePayment;
import com.EMS.Practice_Project.exception.BadRequestException;
import com.EMS.Practice_Project.exception.ResourceNotFoundException;
import com.EMS.Practice_Project.mapper.AdvancePaymentMapper;
import com.EMS.Practice_Project.repository.AdvancePaymentRepository;
import com.EMS.Practice_Project.service.AdvancePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvancePaymentServiceImpl implements AdvancePaymentService {

    @Autowired
    private AdvancePaymentRepository advancePaymentRepository;

    @Autowired
    private AdvancePaymentMapper advancePaymentMapper;

    @Override
    public AdvancePaymentDto requestAdvance(CreateAdvancePaymentRequest request) {
        AdvancePayment record = advancePaymentMapper.toEntity(request);
        record.setAdvanceId("ADV-" + System.currentTimeMillis());
        record.setRequestedDate(request.getRequestedDate() == null ? LocalDate.now() : request.getRequestedDate());
        record.setStatus(AdvancePayment.AdvanceStatus.PENDING);
        record = advancePaymentRepository.save(record);
        return advancePaymentMapper.toDto(record);
    }

    @Override
    public AdvancePaymentDto updateAdvanceStatus(String advanceId, String status) {
        AdvancePayment existing = advancePaymentRepository.findByAdvanceId(advanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Advance request not found"));
        try {
            existing.setStatus(AdvancePayment.AdvanceStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid advance status: " + status);
        }
        if (existing.getStatus() == AdvancePayment.AdvanceStatus.APPROVED) {
            existing.setApprovedDate(LocalDate.now());
        }
        existing = advancePaymentRepository.save(existing);
        return advancePaymentMapper.toDto(existing);
    }

    @Override
    public List<AdvancePaymentDto> getAdvanceRequestsForEmployee(String employeeId) {
        return advancePaymentRepository.findByEmployeeId(employeeId).stream()
                .map(advancePaymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdvancePaymentDto> getAdvanceRequestsByStatus(String status) {
        AdvancePayment.AdvanceStatus advanceStatus;
        try {
            advanceStatus = AdvancePayment.AdvanceStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid advance status: " + status);
        }
        return advancePaymentRepository.findByStatus(advanceStatus).stream()
                .map(advancePaymentMapper::toDto)
                .collect(Collectors.toList());
    }
}
