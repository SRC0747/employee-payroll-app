package com.bridgelabz.employeepayrollapp.repository;

import com.bridgelabz.employeepayrollapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Purpose : To demonstrate all the fields of EmployeePayroll repository connected with JpaRepository format
 *
 * @author : Sampriti Roy Chowdhury
 * @version : 0.0.1
 * @since : 15-12-2021
 */
public interface EmployeePayrollRepository extends JpaRepository<Employee, Integer> {
}
