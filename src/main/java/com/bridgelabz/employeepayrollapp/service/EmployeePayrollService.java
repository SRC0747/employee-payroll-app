package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.builder.EmployeePayrollBuilder;
import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.exception.CustomException;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Purpose : To implement all the methods in controller class
 *
 * @author : Sampriti Roy Chowdhury
 * @version : 0.0.1
 * @since : 15-12-2021
 */

@Service
public class EmployeePayrollService implements IEmployeePayrollService {

    private static final String EMPLOYEE_DETAILS_UPDATED_SUCCESSFULLY = "Employee details of corresponding id are updated successfully";
    private static final String EMPLOYEE_RECORD_DELETED_SUCCESSFULLY = "Employee details of corresponding id are deleted successfully";
    private static final String EMPLOYEE_DETAILS_ADDED_SUCCESSFULLY = "Added successfully";

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    @Autowired
    private EmployeePayrollBuilder employeePayrollBuilder;

    /**
     * Purpose : This method is used to add the details of employee
     *
     * @param employeeDTO defines data added in DTO
     * @return message if data is added successfully;
     */
    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee = employeePayrollBuilder.buildEmployeePayrollEntity(employeeDTO, employee);
        employeePayrollRepository.save(employee);
        return EMPLOYEE_DETAILS_ADDED_SUCCESSFULLY;
    }

    /**
     * Purpose : This method is used to get list of details of all the employees
     *
     * @return the list of employeed added
     */
    @Override
    public List<Employee> getEmployeePayrollData() {
        return employeePayrollRepository.findAll();
    }

    /**
     * Purpose : This method is used to find the details of employee corresponding to the id
     *
     * @param employeeId defines the id of the employee
     * @return the details of the particular employee
     */
    @Override
    public Employee findEmployeeById(int employeeId) {
        return employeePayrollRepository.findById(employeeId).
                orElseThrow(() -> new CustomException("Employee data not found of this id :" + employeeId));
    }

    /**
     * Purpose : This method is used to update the details of the employee of corresponding id
     *
     * @param employeeId defines employee id
     * @param employeeDTO defines the data stores in employee DTO
     * @return the message if updated successfully
     */
    @Override
    public String updateEmployeePayrollById(int employeeId, EmployeeDTO employeeDTO) {
        Employee employee = findEmployeeById(employeeId);
        employee = employeePayrollBuilder.buildEmployeePayrollEntity(employeeDTO, employee);
        employeePayrollRepository.save(employee);
        return EMPLOYEE_DETAILS_UPDATED_SUCCESSFULLY;
    }

    /**
     * Purpose : This method is used to delete the employee details of corressponding id
     *
     * @param employeeId defines employee id
     * @return message if deleted successfully
     */
    @Override
    public String deleteEmployeePayroll(int employeeId) {
        Employee employee = findEmployeeById(employeeId);
        employeePayrollRepository.deleteById(employeeId);
        return EMPLOYEE_RECORD_DELETED_SUCCESSFULLY;
    }
}
