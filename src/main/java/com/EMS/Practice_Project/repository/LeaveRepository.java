package com.EMS.Practice_Project.repository;

import com.EMS.Practice_Project.entity.LeaveRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends MongoRepository<LeaveRequest, String> {

    List<LeaveRequest> findByEmployeeId(String employeeId);

    List<LeaveRequest> findByStatus(LeaveRequest.LeaveStatus status);

    java.util.Optional<LeaveRequest> findByLeaveId(String leaveId);
}
