package com.example.samplespringbootdeploy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.samplespringbootdeploy.dto.EmployeeResponseDto;
import com.example.samplespringbootdeploy.entity.Employee;
import com.example.samplespringbootdeploy.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void getEmployeeById_shouldReturnEmployeeResponse() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Raini");
        employee.setLastName("shivashankar");
        employee.setEmployeeCode("EMP005");
        employee.setEmail("shankar@gmail.com");
        employee.setDesignation("Java Developer");
        employee.setDepartmentId(3L);
        employee.setManagerId(101L);
        employee.setPhoneNumber("9573834394");
        employee.setEmploymentStatus("ACTIVE");
        employee.setJoiningDate(LocalDate.of(2026, 7, 22));
        employee.setSalary(new BigDecimal("700000.00"));
        employee.setCreatedAt(LocalDateTime.of(2026, 8, 6, 11, 36, 55, 412340000));
        employee.setUpdatedAt(LocalDateTime.of(2026, 8, 6, 11, 36, 55, 412340000));

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeResponseDto response = employeeService.getEmployeeById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Raini", response.getFirstName());
        assertEquals("shivashankar", response.getLastName());
        assertEquals("EMP005", response.getEmployeeCode());
        assertEquals("Java Developer", response.getDesignation());
        assertEquals("ACTIVE", response.getEmploymentStatus());
    }

    @Test
    void getAllEmployees_shouldReturnMappedEmployeeList() {
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setFirstName("Raini");
        employee1.setLastName("shivashankar");
        employee1.setEmployeeCode("EMP005");
        employee1.setEmail("shankar@gmail.com");
        employee1.setDesignation("Java Developer");
        employee1.setEmploymentStatus("ACTIVE");

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setFirstName("Karthik");
        employee2.setLastName("Nair");
        employee2.setEmployeeCode("EMP006");
        employee2.setEmail("karthik@gmail.com");
        employee2.setDesignation("Senior Engineer");
        employee2.setEmploymentStatus("ACTIVE");

        when(employeeRepository.findAll()).thenReturn(List.of(employee1, employee2));

        List<EmployeeResponseDto> response = employeeService.getAllEmployees();

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("Raini", response.get(0).getFirstName());
        assertEquals("EMP006", response.get(1).getEmployeeCode());
    }

    @Test
    void getAllEmployees_shouldReturnEmptyList_whenRepositoryIsEmpty() {
        when(employeeRepository.findAll()).thenReturn(List.of());

        List<EmployeeResponseDto> response = employeeService.getAllEmployees();

        assertNotNull(response);
        assertTrue(response.isEmpty());
    }
}
