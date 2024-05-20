package com.group.commute.dto.team.request;

import com.group.commute.domain.employee.Employee;
import com.group.commute.domain.team.Team;

public class TeamCreateRequestDto {
    private String name;

    public String getName() {
        return name;
    }

    public TeamCreateRequestDto(String name) {
        this.name = name;
    }

    protected TeamCreateRequestDto() {

    }

    public Team toEntity() {
        return new Team(this.name);
    }
}
