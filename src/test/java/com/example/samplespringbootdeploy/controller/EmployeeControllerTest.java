package com.example.samplespringbootdeploy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
