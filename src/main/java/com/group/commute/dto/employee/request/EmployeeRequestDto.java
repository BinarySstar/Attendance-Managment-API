package com.group.commute.dto.employee.request;

import com.group.commute.domain.employee.Employee;
import com.group.commute.domain.employee.Role;
import com.group.commute.domain.team.Team;

import java.time.LocalDate;

public class EmployeeRequestDto {
    private String name;
    private String teamName;

    private Role role;
    private LocalDate birthday;
    private LocalDate workStartDate;

    public EmployeeRequestDto(String name, String teamName, Role role, LocalDate birthday, LocalDate workStartDate) {
        this.name = name;
        this.teamName = teamName;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public Role getRole() {
        return role;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getWorkStartDate() {
        return workStartDate;
    }

    public Employee toEntity(Team team) {
        return new Employee(name, team, role, birthday, workStartDate);
    }
}
