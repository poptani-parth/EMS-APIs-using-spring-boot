package com.EMS.Practice_Project.repository;

import com.EMS.Practice_Project.entity.SalaryRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository
public interface SalaryRepository extends MongoRepository<SalaryRecord, String> {

    List<SalaryRecord> findByEmployeeId(String employeeId);

    List<SalaryRecord> findByMonth(YearMonth month);
}
