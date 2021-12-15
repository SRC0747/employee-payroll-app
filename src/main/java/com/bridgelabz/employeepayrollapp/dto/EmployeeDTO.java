package com.bridgelabz.employeepayrollapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 * Purpose : To demonstrate various fields of employee in DTo
 *
 * @author : Sampriti Roy Chowdhury
 * @version : 0.0.1
 * @since : 15-12-2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @Pattern(regexp = "[A-Z][a-z]{2,}$", message = "Not a Valid Name")
    private String name;

    @Size(message = "This should be within 10 letters", max = 10)
    private String gender;


    @Size(message = "This should be within 15 letters", max = 15)
    private String department;

    private long salary;

    @Size(message = "This should be within 50 letters", max = 50)
    private String notes;
}
