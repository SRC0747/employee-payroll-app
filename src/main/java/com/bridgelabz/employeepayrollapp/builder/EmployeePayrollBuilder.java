package com.bridgelabz.employeepayrollapp.builder;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Purpose : Implement builder to handle BeanUtils so that not to ignore boilerPlate not to break dry principle
 *
 * @author : Sampriti Roy Chowdhury
 * @version : 0.0.1
 * @since : 15-12-2021
 */

@Component
public class EmployeePayrollBuilder {

    /**
     * Purpose : This method is used to convert the input of DTO into entity using BeanUtils
     *
     * @param employeeDTO defines the field of employee in DTO
     * @param employee defines the field of employee in entity
     * @return the fiels of employee in the format of entity
     */
    public Employee buildEmployeePayrollEntity(EmployeeDTO employeeDTO, Employee employee) {
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }
}
