package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.builder.EmployeePayrollBuilder;
import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.exception.CustomException;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
        employee1.setDepartments((List.of("Cse")));
        employee1.setJoiningDate("15/01/2021");
        employee1.setSalary((long) 800000.00);
        employee1.setNotes("Regular");
        employeeEntityList.add(employee1);
        Employee employee2 = new Employee();
        employee2.setName("Sreelipta");
        employee2.setGender("Female");
        employee2.setDepartments((List.of("Backend")));
        employee2.setJoiningDate("17/02/2021");
        employee2.setSalary((long) 823600.00);
        employee2.setNotes("Regular");
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
        employeeDTO.setName("Sampriti");
        employeeDTO.setGender("Female");
        employeeDTO.setDepartments((List.of("Cse")));
        employeeDTO.setJoiningDate("15/01/2021");
        employeeDTO.setSalary((long) 36239.25);
        employeeDTO.setNotes("Regular");
        when(employeePayrollBuilder.buildEmployeePayrollEntity(employeeDTO, employee)).thenReturn(employee);
        String actualValue = employeePayrollService.addEmployee(employeeDTO);
        assertEquals("Added successfully", actualValue);
    }

    @Test
    void givenwhenUpdateEmployeeDataCalled_ShouldUpdateEmployeeDataAndReturnSuccessMessage() {
        int employeeId = 1;
        ArgumentCaptor<Employee> employeePayrollArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        String successMessage = "Employee details of corresponding id are updated successfully";
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Sampriti");
        employeeDTO.setSalary(20000);
        employeeDTO.setGender("Female");
        employeeDTO.setDepartments((List.of("Cse")));
        employeeDTO.setJoiningDate("15/01/2021");
        employeeDTO.setNotes("Regular");

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Sampriti");
        employee.setSalary(20000);
        employee.setGender("Female");
        employee.setDepartments((List.of("Cse")));
        employee.setJoiningDate("15/01/2021");
        employee.setNotes("Regular");

        when(employeePayrollRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(employeePayrollBuilder.buildEmployeePayrollEntity(employeeDTO, employee)).thenReturn(employee);
        String actualMessage = employeePayrollService.updateEmployeePayrollById(employeeId, employeeDTO);
        assertEquals(successMessage, actualMessage);
        verify(employeePayrollRepository, times(1)).save(employeePayrollArgumentCaptor.capture());
        assertEquals(employee.getName(), employeePayrollArgumentCaptor.getValue().getName());
        assertEquals(employee.getSalary(), employeePayrollArgumentCaptor.getValue().getSalary());
        assertEquals(employee.getGender(), employeePayrollArgumentCaptor.getValue().getGender());
        assertEquals(employee.getDepartments(), employeePayrollArgumentCaptor.getValue().getDepartments());
        assertEquals(employee.getNotes(), employeePayrollArgumentCaptor.getValue().getNotes());

    }

    @Test
    void whenUpdateEmployeeMethodIsCalled_IfNotFoundId_shouldThrowExceptionMessage() {
        int id = 1;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Sampriti");
        employeeDTO.setGender("Female");
        employeeDTO.setDepartments((List.of("Cse")));
        employeeDTO.setJoiningDate("15/01/2021");
        employeeDTO.setSalary((long) 32635.25);
        employeeDTO.setNotes("Regular");
        when(employeePayrollRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> employeePayrollService.updateEmployeePayrollById(id,  employeeDTO));
    }

    @Test
    void givenDeleteEmployeeMethodIsCalledWithAnId_ShouldDeleteTheDataOfThatId() {
        int id = 1;
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Rahul");
        employee.setGender("Male");
        employee.setDepartments((List.of("Ece")));
        employee.setJoiningDate("25/04/2021");
        employee.setSalary((long) 35236.26);
        employee.setNotes("Not Regular");
        when(employeePayrollRepository.findById(id)).thenReturn(Optional.of(employee));
        String actualMessage = employeePayrollService.deleteEmployeePayroll(id);
        assertEquals("Employee details of corresponding id are deleted successfully", actualMessage);
    }


    @Test
    void whenDeleteEmployeeMethodIsCalled_IfIdNotFound_shouldThrowExceptionMessage() {
        int id = 1;
        when(employeePayrollRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> employeePayrollService.deleteEmployeePayroll(id));
    }
}