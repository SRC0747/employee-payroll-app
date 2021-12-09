package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeePayrollService;

    @GetMapping(value = "/welcome")
    public String welcome() {
        return "Welcome to Employee Payroll App Server.";
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addEmployeePayrollData(
            @RequestBody EmployeeDTO employeePayrollDto) {
        Employee employeePayRollData = null;
        employeePayRollData = employeePayrollService.addEmployee(employeePayrollDto);
        ResponseDTO responseDto = new ResponseDTO("Created Employee Payroll Data For ", employeePayRollData);
        return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
    }
}
