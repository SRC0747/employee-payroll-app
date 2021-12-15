package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;

import java.util.List;

/**
 * Purpose : To implement all the methods of service class
 *
 * @author : Sampriti Roy Chowdhury
 * @version : 0.0.1
 * @since : 15-12-2021
 */

public interface IEmployeePayrollService {

    String addEmployee(EmployeeDTO employeePayrollDto);

    List<Employee> getEmployeePayrollData();

    Employee findEmployeeById(int empId);

    String updateEmployeePayrollById(int empId, EmployeeDTO employeeDTO);

    public String deleteEmployeePayroll(int empId);
}
