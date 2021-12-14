package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.builder.EmployeePayrollBuilder;
import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.exception.CustomException;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeePayrollService implements IEmployeePayrollService {

    private static final String EMPLOYEE_DETAILS_UPDATED_SUCCESSFULLY = "Employee details of corresponding id are updated successfully";
    private static final String EMPLOYEE_RECORD_DELETED_SUCCESSFULLY = "Employee details of corresponding id are deleted successfully";

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    @Autowired
    private EmployeePayrollBuilder employeePayrollBuilder;

//    @Override
//    public Employee addEmployee(EmployeeDTO employeeDTO) {
//        //Employee employee = modelMapper.map(employeeDTO, Employee.class);
//        Employee employee = new Employee();
//        employee = employeePayrollBuilder.buildEmployeePayrollEntity(employeeDTO, employee);
//        //BeanUtils.copyProperties(employeeDTO, employee);
//        employeePayrollRepository.save(employee);
//        return employeePayrollRepository.save(employee);
//    }

    @Override
    public String addEmployee(EmployeeDTO employeeDto) {
        Employee employee = new Employee();
        employee = employeePayrollBuilder.buildEmployeePayrollEntity(employeeDto, employee);
        employeePayrollRepository.save(employee);
        //return employeePayrollRepository.save(employee);
        return "Added successfully";
    }

    @Override
    public List<Employee> getEmployeePayrollData() {
        return employeePayrollRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(int empId) {
        return employeePayrollRepository.findById(empId).
                orElseThrow(() -> new CustomException("Employee data not found of this id :" + empId));
    }

    @Override
    public String updateEmployeePayrollById(int empId, EmployeeDTO employeeDTO) {
        Employee employee = findEmployeeById(empId);
        employee = employeePayrollBuilder.buildEmployeePayrollEntity(employeeDTO, employee);
        System.out.println(employee.toString());
        employeePayrollRepository.save(employee);
        return EMPLOYEE_DETAILS_UPDATED_SUCCESSFULLY;
    }

    @Override
    public String deleteEmployeePayroll(int empId) {
        Employee employee = findEmployeeById(empId);
        employeePayrollRepository.deleteById(empId);
        return EMPLOYEE_RECORD_DELETED_SUCCESSFULLY;
    }
}
