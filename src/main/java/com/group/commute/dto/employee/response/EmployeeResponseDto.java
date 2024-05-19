package com.group.commute.dto.employee.response;

import com.group.commute.domain.employee.Role;
import com.group.commute.domain.team.Team;

import java.time.LocalDate;

public class EmployeeResponseDto {
    private String name;
    private String teamName;

    private Role role;
    private LocalDate birthday;
    private LocalDate workStartDate;

    public EmployeeResponseDto(String name, Team team, Role role, LocalDate birthday, LocalDate workStartDate) {
        this.name = name;
        this.teamName = team.getName();
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
}
