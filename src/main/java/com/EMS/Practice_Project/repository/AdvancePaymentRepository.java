package com.EMS.Practice_Project.repository;

import com.EMS.Practice_Project.entity.AdvancePayment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvancePaymentRepository extends MongoRepository<AdvancePayment, String> {

    List<AdvancePayment> findByEmployeeId(String employeeId);

    List<AdvancePayment> findByStatus(AdvancePayment.AdvanceStatus status);

    java.util.Optional<AdvancePayment> findByAdvanceId(String advanceId);
}
