package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Purpose : To demonstrate various HTTP request methods
 *
 * @author : Sampriti Roy Chowdhury
 * @version : 0.0.1
 * @since : 15-12-2021
 */

@RestController
@RequestMapping("/api")
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeePayrollService;

    /**
     * Purpose : This method is used to print the welcome message
     *
     * @return welcome message
     */
    @GetMapping(value = "/welcome")
    public String welcome() {
        return "Welcome to Employee Payroll App Server.";
    }

    /**
     * Purpose : This method to add new employee data in employee payroll service
     *
     * @param employeePayrollDto defines the input data of employee in DTO  format
     * @return response message if new employee data is added
     */
    @PostMapping("/create")
    public ResponseEntity<String> addEmployeePayrollData(
            @RequestBody EmployeeDTO employeePayrollDto) {
        String employeePayRollData = employeePayrollService.addEmployee(employeePayrollDto);
        ResponseDTO responseDTO = new ResponseDTO("Created Employee Payroll Data For ", employeePayRollData);
        return new ResponseEntity<>("Created Employee Payroll Data For", HttpStatus.OK);
    }

    /**
     * Purpose : This method is used to get list of all the employees added
     *
     * @return the list of employee details
     */
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
        List<Employee> employeePayRollData = employeePayrollService.getEmployeePayrollData();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", employeePayRollData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose : This method is used to get the employee data by using particular id
     *
     * @param employeeId defines employee id
     * @return employee data corresponding to the  id
     */
    @GetMapping("/get/{employeeId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollDataById(
            @PathVariable int employeeId) {
        Employee employeePayRollData = employeePayrollService.findEmployeeById(employeeId);
        ResponseDTO responseDto = new ResponseDTO("Get Call Success For Id", employeePayRollData);
        return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
    }

    /**
     * Purpose : This method is used to update the employee data corresponding to the id
     *
     * @param employeeId defines the employee id
     * @param employeeDTO defines the data in DTO format
     * @return message if data is updated successfully
     */
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<String> updateEmployeePayroll(
            @PathVariable int employeeId,
            @RequestBody EmployeeDTO employeeDTO) {
        String employeePayRollData = employeePayrollService.updateEmployeePayrollById(employeeId, employeeDTO);
        ResponseDTO responseDTO = new ResponseDTO("Update Employee Payroll Data For ", employeePayRollData);
        return new ResponseEntity<>("Update Employee Payroll Data For", HttpStatus.OK);

    }

    /**
     * Purpose : This method is used to delete the employee details corresponding to it's id
     *
     * @param employeeId defines employee id
     * @return message if employee details of corresponding id is deleted successfully
     */
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployeePayroll(
            @PathVariable int employeeId) {
        employeePayrollService.deleteEmployeePayroll(employeeId);
        ResponseDTO responseDto = new ResponseDTO("Deleted Successfully", "deleted id: " + employeeId);
        //return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}
