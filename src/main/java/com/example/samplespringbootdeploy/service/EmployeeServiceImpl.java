package com.example.samplespringbootdeploy.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.samplespringbootdeploy.dto.EmployeeResponseDto;
import com.example.samplespringbootdeploy.entity.Employee;
import com.example.samplespringbootdeploy.exception.ResourceNotFoundException;
import com.example.samplespringbootdeploy.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        EmployeeResponseDto response = new EmployeeResponseDto();
        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmployeeCode(employee.getEmployeeCode());
        response.setEmail(employee.getEmail());
        response.setPhoneNumber(employee.getPhoneNumber());
        response.setDesignation(employee.getDesignation());
        response.setDepartmentId(employee.getDepartmentId());
        response.setManagerId(employee.getManagerId());
        response.setSalary(employee.getSalary());
        response.setEmploymentStatus(employee.getEmploymentStatus());
        response.setJoiningDate(employee.getJoiningDate());
        response.setCreatedAt(employee.getCreatedAt() != null ? employee.getCreatedAt() : LocalDateTime.now());
        response.setUpdatedAt(employee.getUpdatedAt() != null ? employee.getUpdatedAt() : LocalDateTime.now());

        return response;
    }
}
