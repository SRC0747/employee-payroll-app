package com.bridgelabz.employeepayrollapp.integration;

import com.bridgelabz.employeepayrollapp.controller.EmployeePayrollController;
import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeePayrollController.class)
public class EmployeePayrollControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeePayrollService employeePayrollService;

    @Test
    void getAllEmployeePayrollData() throws Exception {
        when(employeePayrollService.getEmployeePayrollData()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get"))
                .andExpect(status().isOk());
    }

    @Test
    void addEmployeePayrollData() throws Exception {
        when(employeePayrollService.addEmployee(any())).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/create")
                        .content("{\"name\":\"Sampriti\",\"salary\":30000,\"gender\":\"Female\"," +
                                "\"joiningDate\":\"09/21/2021\",\"department\":[\"Cse,Development,Testing\"]," +
                                "\"notes\":\"Regular\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmployeePayrollData() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Sampriti");
        employeeDTO.setGender("Female");
        employeeDTO.setSalary(25000);
        employeeDTO.setDepartments((List.of("IT")));
        employeeDTO.setJoiningDate("15/01/2021");
        employeeDTO.setNotes("Regular");
        int id = 1;
        when(employeePayrollService.updateEmployeePayrollById(id,employeeDTO)).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/update/1")
                        .content("{\"name\":\"Sampriti\",\"salary\":25000,\"gender\":\"Female\"," +
                                "\"joiningDate\":\"09/21/2021\",\"department\":[\"Cse,Development,Testing\"]," +
                                "\"notes\":\"Regular\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployeePayrollData() throws Exception {
        when(employeePayrollService.deleteEmployeePayroll(1)).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/delete/1")
                        .content("{\"name\":\"Sampriti\",\"salary\":25000,\"gender\":\"Female\"," +
                                "\"joiningDate\":\"09/21/2021\",\"department\":[\"Cse,Development,Testing\"]," +
                                "\"notes\":\"Regular\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}
