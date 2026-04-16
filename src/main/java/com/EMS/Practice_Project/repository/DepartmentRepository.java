package com.EMS.Practice_Project.repository;

import com.EMS.Practice_Project.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {

    Optional<Department> findByDepartmentId(String departmentId);

    boolean existsByDepartmentId(String departmentId);
}
