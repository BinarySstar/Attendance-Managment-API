package com.group.commute.controller;

import com.group.commute.dto.employee.request.EmployeeRequestDto;
import com.group.commute.dto.employee.response.EmployeeResponseDto;
import com.group.commute.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponseDto> getEmployee() {
        return employeeService.getEmployee();
    }

    @PostMapping
    public void saveEmployee(@RequestBody EmployeeRequestDto requestDto){
        employeeService.saveEmployee(requestDto);
    }
}
