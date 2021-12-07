package com.bridgelabz.employeepayrollapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeePayrollAppController {

    @GetMapping(value = "/welcome")
    public String welcome() {
        return "Welcome to Employee Payroll App Server.";
    }


}
