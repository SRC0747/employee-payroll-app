package com.bridgelabz.employeepayrollapp.builder;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeePayrollBuilder {

    public Employee buildEmployeePayrollEntity(EmployeeDTO employeeDTO, Employee employee) {
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }
}
