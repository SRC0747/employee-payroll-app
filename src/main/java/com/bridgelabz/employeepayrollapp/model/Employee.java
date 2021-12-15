package com.bridgelabz.employeepayrollapp.model;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
