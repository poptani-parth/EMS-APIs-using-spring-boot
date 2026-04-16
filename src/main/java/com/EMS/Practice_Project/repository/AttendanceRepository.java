package com.EMS.Practice_Project.repository;

import com.EMS.Practice_Project.entity.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String> {

    List<Attendance> findByEmployeeIdAndDateBetween(String employeeId, LocalDate start, LocalDate end);

    List<Attendance> findByEmployeeId(String employeeId);

    List<Attendance> findByDate(LocalDate date);
}
