package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.builder.EmployeePayrollBuilder;
import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.exception.CustomException;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeePayrollServiceTest {

    @InjectMocks
    private EmployeePayrollService employeePayrollService;

    @Mock
    private EmployeePayrollRepository employeePayrollRepository;

    @Mock
    private EmployeePayrollBuilder employeePayrollBuilder;

    @Test
    void whenGetAllEmployeeMethodIsCalled_ShouldReturnListOfEmployeeResponseDto() {

        List<Employee> employeeEntityList = new ArrayList<>();

        Employee employee1 = new Employee();
        employee1.setName("Sampriti");
        employee1.setGender("Female");
        employee1.setDepartment("Backend");
        employee1.setSalary((long) 800000.00);
        employeeEntityList.add(employee1);
        Employee employee2 = new Employee();
        employee2.setName("Sreelipta");
        employee2.setGender("Female");
        employee2.setDepartment("Backend");
        employee1.setSalary((long) 800000.00);
        employeeEntityList.add(employee2);

        when(employeePayrollRepository.findAll()).thenReturn(employeeEntityList);
        List<Employee> actualListOfEmployeeEntity = employeePayrollService.getEmployeePayrollData();
        assertEquals(2, actualListOfEmployeeEntity.size());
        assertEquals(employeeEntityList, actualListOfEmployeeEntity);
    }

    @Test
    void WhenFindEmployeeDetailsByIdCalled_ThenIfIdIsNotFound_ShouldThrowException() {
        int employeeId = 1;
        when(employeePayrollRepository.findById(employeeId)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> employeePayrollService.findEmployeeById(employeeId));
    }

    @Test
    void whenAddEmployeeEntitiesCalled_ShouldAddEmployeeDetailsAndReturnResponseAndGenerateSuccessMessage() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = new Employee();
//        employee.setName("Sampriti");
//        employee.setGender("Female");
//        employee.setDepartment("Backend");
//        employee.setSalary((long) 36239.25);
        employeeDTO.setName("Sampriti");
        employeeDTO.setGender("Female");
        employeeDTO.setDepartment("Backend");
        employeeDTO.setSalary((long) 36239.25);
        when(employeePayrollBuilder.buildEmployeePayrollEntity(employeeDTO, employee)).thenReturn(employee);
        String actualValue = employeePayrollService.addEmployee(employeeDTO);
        assertEquals("Added successfully", actualValue);
    }

    @Test
    void whenEditEmployeeMethodIsCalled_IfNotFoundId_shouldThrowExceptionMessage() {
        int id = 1;
        EmployeeDTO payrollDto = new EmployeeDTO();
        payrollDto.setName("Sampriti");
        payrollDto.setGender("Female");
        payrollDto.setDepartment("Backend");

        when(employeePayrollRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> employeePayrollService.updateEmployeePayrollById(id,  payrollDto));
    }

    @Test
    void whenDeleteEmployeeMethodIsCalled_IfIdNotFound_shouldThrowExceptionMessage() {
        int id = 1;
        when(employeePayrollRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> employeePayrollService.deleteEmployeePayroll(id));
    }

    @Test
    void givenDeleteEmployeeMethodIsCalledWithAnId_ShouldDeleteTheDataOfThatId() {
        int id = 1;
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Sampriti");
        employee.setGender("Female");
        employee.setDepartment("Backend");

        when(employeePayrollRepository.findById(id)).thenReturn(Optional.of(employee));
        String actualMessage = employeePayrollService.deleteEmployeePayroll(id);
        assertEquals("Employee details of corresponding id are deleted successfully", actualMessage);
       // verify(employeePayrollRepository, times(1)).delete(employee);
    }
}