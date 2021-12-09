package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeePayrollService implements IEmployeePayrollService {

    private static final String EMPLOYEE_DETAILS_DELETED = "Employee details of corresponding id are deleted successfully";
    private static final String EMPLOYEE_DETAILS_NOT_FOUND = "Employee details of corresponding id are not found";

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    @Override
    public Employee addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        return employeePayrollRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeePayrollData() {
        return employeePayrollRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int empId) {
        return employeePayrollRepository.findById(empId)
                .orElse(null);
    }

    @Override
    public Employee updateEmployeePayrollById(int empId, EmployeeDTO employeeDTO) {
        Employee employee = employeePayrollRepository.findById(empId)
                .orElse(null);
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        //employeePayRollData.updateEmployeePayrollData(employeeDTO);
        return employeePayrollRepository.save(employee);
    }

    @Override
    public String deleteEmployeePayroll(int empId) {
        Optional<Employee> employee = employeePayrollRepository.findById(empId);
        if (employee.isPresent()) {
            employeePayrollRepository.delete(employee.get());
            return EMPLOYEE_DETAILS_DELETED;
        }
        return EMPLOYEE_DETAILS_NOT_FOUND;
    }
}
