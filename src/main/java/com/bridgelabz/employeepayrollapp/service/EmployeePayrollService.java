package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeePayrollService implements IEmployeePayrollService {

    private final List<Employee> employeePayRollDataList = new ArrayList<>();

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    @Override
    public Employee addEmployee(EmployeeDTO employeeDTO) {
        Employee employeePayRollData = null;
        employeePayRollData = new Employee(employeeDTO);
        employeePayRollDataList.add(employeePayRollData);
        return employeePayrollRepository.save(employeePayRollData);
    }

}
