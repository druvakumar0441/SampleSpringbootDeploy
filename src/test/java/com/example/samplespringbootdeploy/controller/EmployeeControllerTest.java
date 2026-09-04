package com.example.samplespringbootdeploy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import com.example.samplespringbootdeploy.dto.EmployeeResponseDto;
import com.example.samplespringbootdeploy.exception.ResourceNotFoundException;
import com.example.samplespringbootdeploy.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void getEmployeeById_shouldReturnEmployeeResponse() throws Exception {
        EmployeeResponseDto response = new EmployeeResponseDto();
        response.setId(1L);
        response.setFirstName("Raini");
        response.setLastName("shivashankar");
        response.setEmployeeCode("EMP005");
        response.setEmail("shankar@gmail.com");
        response.setDesignation("Java Developer");
        response.setDepartmentId(3L);
        response.setManagerId(101L);
        response.setPhoneNumber("9573834394");
        response.setEmploymentStatus("ACTIVE");
        response.setJoiningDate(LocalDate.of(2026, 7, 22));
        response.setSalary(new BigDecimal("700000.00"));
        response.setCreatedAt(LocalDateTime.of(2026, 8, 6, 11, 36, 55, 412340000));
        response.setUpdatedAt(LocalDateTime.of(2026, 8, 6, 11, 36, 55, 412340000));

        when(employeeService.getEmployeeById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Raini"))
                .andExpect(jsonPath("$.lastName").value("shivashankar"))
                .andExpect(jsonPath("$.employeeCode").value("EMP005"))
                .andExpect(jsonPath("$.designation").value("Java Developer"))
                .andExpect(jsonPath("$.employmentStatus").value("ACTIVE"));
    }

    @Test
    void getEmployeeById_shouldReturnStructuredErrorResponse_whenEmployeeNotFound() throws Exception {
        when(employeeService.getEmployeeById(99L))
                .thenThrow(new ResourceNotFoundException("Employee not found with id: 99"));

        mockMvc.perform(get("/api/employees/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.error").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("Employee not found with id: 99"))
                .andExpect(jsonPath("$.path").value("/api/employees/99"));
    }

    @Test
    void getAllEmployees_shouldReturnEmployeeList() throws Exception {
        EmployeeResponseDto employee1 = new EmployeeResponseDto();
        employee1.setId(1L);
        employee1.setFirstName("Raini");
        employee1.setLastName("shivashankar");
        employee1.setEmployeeCode("EMP005");
        employee1.setEmail("shankar@gmail.com");
        employee1.setDesignation("Java Developer");
        employee1.setEmploymentStatus("ACTIVE");

        EmployeeResponseDto employee2 = new EmployeeResponseDto();
        employee2.setId(2L);
        employee2.setFirstName("Karthik");
        employee2.setLastName("Nair");
        employee2.setEmployeeCode("EMP006");
        employee2.setEmail("karthik@gmail.com");
        employee2.setDesignation("Senior Engineer");
        employee2.setEmploymentStatus("ACTIVE");

        when(employeeService.getAllEmployees()).thenReturn(List.of(employee1, employee2));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("Raini"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].employeeCode").value("EMP006"));
    }

    @Test
    void getAllEmployees_shouldReturnEmptyArray_whenNoEmployeesExist() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(List.of());

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getEmployeeById_shouldReturnBadRequest_whenIdIsNotNumeric() throws Exception {
        mockMvc.perform(get("/api/employees/abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error").value("BAD_REQUEST"));
    }
}
