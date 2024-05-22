package com.group.commute.dto.team.request;

import com.group.commute.domain.employee.Employee;
import com.group.commute.domain.team.Team;

public class TeamCreateRequestDto {
    private String name;

    private int dayOffApplyDay;

    public String getName() {
        return name;
    }

    public int getDayOffApplyDay() {
        return dayOffApplyDay;
    }

    public TeamCreateRequestDto(String name, int dayOffApplyDay) {
        this.name = name;
        this.dayOffApplyDay = dayOffApplyDay;
    }

    protected TeamCreateRequestDto() {

    }

    public Team toEntity() {
        return new Team(this.name);
    }
}
