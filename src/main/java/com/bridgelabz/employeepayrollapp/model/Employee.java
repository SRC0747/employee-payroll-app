package com.bridgelabz.employeepayrollapp.model;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Purpose : To demonstrate all the fields of employee in the entity databse format
 *
 * @author : Sampriti Roy Chowdhury
 * @version : 0.0.1
 * @since : 15-12-2021
 */

@Data
@Table(name = "employee")
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="EMP_ID")
    private int id;

    @Column(name = "NAME", length=25)
    private String name;

    @Column(name = "GENDER", length = 11)
    private String gender;

    @Column(name = "DEPARTMENT", length = 35)
    private String department;

    @Column(name = "SALARY", length = 30)
    private long salary;

    @Column(name = "NOTES", length = 40)
    private String notes;
}
