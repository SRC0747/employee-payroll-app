package com.bridgelabz.employeepayrollapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

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

    @Pattern(regexp = "Male|Female", message = "Gender Should be either Male or Female")
    private String gender;


    @Size(message = "This should be within 15 letters", max = 15)
    private List<String> departments;

    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$",
            message = "Date should be maintain the format : mm/dd/yyyy")
    private String joiningDate;

    @Min(value = 10000, message = "Minimum wage should be more than 10000")
    private long salary;

    @Size(message = "This should be within 50 letters", max = 50)
    private String notes;
}
