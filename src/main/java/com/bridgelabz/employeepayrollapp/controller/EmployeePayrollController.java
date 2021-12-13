package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/rest/auth")
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
        Employee employeePayRollData = employeePayrollService.addEmployee(employeePayrollDto);
        ResponseDTO responseDTO = new ResponseDTO("Created Employee Payroll Data For ", employeePayRollData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
        List<Employee> employeePayRollData = employeePayrollService.getEmployeePayrollData();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", employeePayRollData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollDataById(
            @PathVariable int empId) {
        Employee employeePayRollData = employeePayrollService.findEmployeeById(empId);
        ResponseDTO responseDto = new ResponseDTO("Get Call Success For Id", employeePayRollData);
        return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayroll(
            @PathVariable int empId,
            @RequestBody EmployeeDTO employeeDTO) {
        String employeePayRollData = employeePayrollService.updateEmployeePayrollById(empId, employeeDTO);
        ResponseDTO responseDTO = new ResponseDTO("Update Employee Payroll Data For ", employeePayRollData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayroll(
            @PathVariable int empId) {
        employeePayrollService.deleteEmployeePayroll(empId);
        ResponseDTO responseDto = new ResponseDTO("Deleted Successfully", "deleted id: " + empId);
        return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
    }
}
