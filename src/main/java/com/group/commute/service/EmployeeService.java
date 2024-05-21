package com.group.commute.service;

import com.group.commute.domain.employee.Employee;
import com.group.commute.domain.employee.EmployeeRepository;
import com.group.commute.domain.employee.Role;
import com.group.commute.domain.team.Team;
import com.group.commute.domain.team.TeamRepository;
import com.group.commute.dto.employee.request.EmployeeRequestDto;
import com.group.commute.dto.employee.response.EmployeeResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final TeamRepository teamRepository;

    public EmployeeService(EmployeeRepository employeeRepository, TeamRepository teamRepository) {
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> getEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(Employee::toDto)
                .toList();
    }

    @Transactional
    public void saveEmployee(EmployeeRequestDto requestDto) {
        Team team = teamRepository.findByName(requestDto.getTeamName())
                .orElseThrow(IllegalArgumentException::new);
        team.addMemberCount(1);
        Employee employee = requestDto.toEntity(team);
        if(employee.getRole() == Role.MANAGER) {
            team.setManager(employee);
        }
        teamRepository.save(team);
        employeeRepository.save(employee);
    }
}
