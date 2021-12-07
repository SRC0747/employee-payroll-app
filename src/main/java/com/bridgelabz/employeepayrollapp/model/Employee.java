package com.bridgelabz.employeepayrollapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "employee")
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private long salary;
}
