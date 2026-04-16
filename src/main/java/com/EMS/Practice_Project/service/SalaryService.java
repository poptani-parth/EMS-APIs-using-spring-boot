package com.EMS.Practice_Project.service;

import com.EMS.Practice_Project.dto.salary.CreateSalaryRequest;
import com.EMS.Practice_Project.dto.salary.SalaryDto;

import java.time.YearMonth;
import java.util.List;

public interface SalaryService {

    SalaryDto createSalaryRecord(CreateSalaryRequest request);

    SalaryDto calculateNetSalary(String salaryId);

    List<SalaryDto> getSalaryByEmployee(String employeeId);

    List<SalaryDto> getSalaryByMonth(YearMonth month);
}
