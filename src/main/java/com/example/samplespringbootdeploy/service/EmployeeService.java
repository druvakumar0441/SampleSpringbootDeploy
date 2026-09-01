package com.example.samplespringbootdeploy.service;

import com.example.samplespringbootdeploy.dto.EmployeeResponseDto;

public interface EmployeeService {

    EmployeeResponseDto getEmployeeById(Long id);
}
