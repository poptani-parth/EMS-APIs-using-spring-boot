package com.EMS.Practice_Project.repository;

import com.EMS.Practice_Project.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByEmployeeId(String employeeId);

    List<Employee> findByDepartmentId(String departmentId);

    boolean existsByEmployeeId(String employeeId);
}
