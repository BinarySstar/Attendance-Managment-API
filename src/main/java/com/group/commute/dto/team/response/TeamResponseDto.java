package com.group.commute.dto.team.response;

import com.group.commute.domain.employee.Employee;

public class TeamResponseDto {

    private String name;
    private String manager;
    private long memberCount;

    private int dayOffApplyday;

    public TeamResponseDto(String name, String manager, long memberCount, int dayOffApplyday) {
        this.name = name;
        this.manager = manager;
        this.memberCount = memberCount;
        this.dayOffApplyday = dayOffApplyday;
    }

    public String getName() {
        return name;
    }

    public String getManager() {
        return manager;
    }

    public long getMemberCount() {
        return memberCount;
    }

    public int getDayOffApplyday() {
        return dayOffApplyday;
    }
}
