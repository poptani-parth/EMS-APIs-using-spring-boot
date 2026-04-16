package com.EMS.Practice_Project.mapper;

import com.EMS.Practice_Project.dto.attendance.AttendanceDto;
import com.EMS.Practice_Project.dto.attendance.CreateAttendanceRequest;
import com.EMS.Practice_Project.entity.Attendance;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

    public AttendanceDto toDto(Attendance entity) {
        if (entity == null) {
            return null;
        }
        AttendanceDto dto = new AttendanceDto();
        dto.setId(entity.getId());
        dto.setAttendanceId(entity.getAttendanceId());
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setDate(entity.getDate());
        dto.setCheckIn(entity.getCheckIn());
        dto.setCheckOut(entity.getCheckOut());
        dto.setStatus(entity.getStatus() != null ? entity.getStatus().name() : null);
        return dto;
    }

    public Attendance toEntity(CreateAttendanceRequest request) {
        if (request == null) {
            return null;
        }
        Attendance entity = new Attendance();
        entity.setEmployeeId(request.getEmployeeId());
        entity.setDate(request.getDate());
        entity.setCheckIn(request.getCheckIn());
        entity.setCheckOut(request.getCheckOut());
        if (request.getStatus() != null) {
            try {
                entity.setStatus(Attendance.AttendanceStatus.valueOf(request.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                entity.setStatus(null);
            }
        }
        return entity;
    }
}
