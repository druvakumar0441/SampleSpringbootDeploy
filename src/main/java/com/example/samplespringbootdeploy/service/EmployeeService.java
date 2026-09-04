package com.example.samplespringbootdeploy.service;

import java.util.List;

import com.example.samplespringbootdeploy.dto.EmployeeResponseDto;

public interface EmployeeService {

    EmployeeResponseDto getEmployeeById(Long id);

    List<EmployeeResponseDto> getAllEmployees();
}
