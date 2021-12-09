package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;

public interface IEmployeePayrollService {

    Employee addEmployee(EmployeeDTO employeePayrollDto);
}
