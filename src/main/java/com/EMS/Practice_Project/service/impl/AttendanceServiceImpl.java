package com.EMS.Practice_Project.service.impl;

import com.EMS.Practice_Project.dto.attendance.AttendanceDto;
import com.EMS.Practice_Project.dto.attendance.CreateAttendanceRequest;
import com.EMS.Practice_Project.entity.Attendance;
import com.EMS.Practice_Project.exception.BadRequestException;
import com.EMS.Practice_Project.mapper.AttendanceMapper;
import com.EMS.Practice_Project.repository.AttendanceRepository;
import com.EMS.Practice_Project.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public AttendanceDto recordAttendance(CreateAttendanceRequest request) {
        if (request.getCheckIn() == null && request.getCheckOut() == null) {
            throw new BadRequestException("Either check-in or check-out timestamp is required");
        }

        Attendance attendance = attendanceMapper.toEntity(request);
        attendance.setAttendanceId("ATT-" + System.currentTimeMillis());
        attendance = attendanceRepository.save(attendance);
        return attendanceMapper.toDto(attendance);
    }

    @Override
    public List<AttendanceDto> getAttendanceForEmployee(String employeeId, LocalDate start, LocalDate end) {
        return attendanceRepository.findByEmployeeIdAndDateBetween(employeeId, start, end).stream()
                .map(attendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDto> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date).stream()
                .map(attendanceMapper::toDto)
                .collect(Collectors.toList());
    }
}
