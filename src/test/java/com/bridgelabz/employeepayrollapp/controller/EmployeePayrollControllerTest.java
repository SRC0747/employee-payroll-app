package com.bridgelabz.employeepayrollapp.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeePayrollControllerTest {

    @InjectMocks
    private EmployeePayrollController employeePayrollController;

    @Mock
    private EmployeePayrollService employeePayrollService;


    @Test
    void whenGetAllEmployeeMethodIsCalled_ShouldReturnTheListOfAllEmployeeResponseDto() {

        List<Employee> employee = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setName("Sampriti");
        employee1.setGender("Female");
        employee1.setDepartment("Backend");
        employee1.setSalary((long) 32639.25);

        Employee employee2 = new Employee();
        employee2.setName("Rita");
        employee2.setGender("Female");
        employee2.setDepartment("Backend");
        employee2.setSalary((long) 556639.25);

        String successString = "Get Call Success";
        int actual = 200;
       // ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(successString, HttpStatus.OK);
        when(employeePayrollService.getEmployeePayrollData()).thenReturn(employee);
        int actualValue = employeePayrollController.getEmployeePayrollData().getStatusCodeValue();
        assertEquals(actual, actualValue);
        }

    @Test
    void whenAddEmployeeMethodIsCalled_ShouldAddEmployeeAndGenerateSuccessMessage() {
        String successString = "Created Employee Payroll Data For";
        ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(successString, HttpStatus.OK);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Sampriti");
        employeeDTO.setGender("Female");
        employeeDTO.setDepartment("Backend");
        employeeDTO.setSalary((long) 32563.26);
        when(employeePayrollService.addEmployee(employeeDTO)).thenReturn(successString);
        ResponseEntity<String> actualResponseString = employeePayrollController.addEmployeePayrollData(employeeDTO);
        assertEquals(expectedResponseEntity, actualResponseString);
    }

    @Test
    void whenUpdateEmployeeMethodIsCalled_ShouldUpdateEmployeeAndGenerateSuccessMessage() {
        String successString = "Update Employee Payroll Data For";
        ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(successString, HttpStatus.OK);
        int id = 1;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Sampriti");
        employeeDTO.setGender("Female");
        employeeDTO.setDepartment("Backend");
        when(employeePayrollService.updateEmployeePayrollById(id, employeeDTO)).thenReturn(successString);
        ResponseEntity<String> actualResponseString = employeePayrollController.updateEmployeePayroll(id, employeeDTO);
        assertEquals(expectedResponseEntity, actualResponseString);
    }

    @Test
    void whenDeleteEmployeeMethodIsCalled_ShouldDeleteEmployeeAndGenerateSuccessMessage() {
        String successString = "Deleted Successfully";
        ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(successString, HttpStatus.OK);
        int id = 1;
        when(employeePayrollService.deleteEmployeePayroll(id)).thenReturn(successString);
        ResponseEntity<String> actualResponseString = employeePayrollController.deleteEmployeePayroll(id);
        assertEquals(expectedResponseEntity, actualResponseString);
    }


}
