package com.group.commute.dto.team.response;

import com.group.commute.domain.employee.Employee;

public class TeamResponseDto {

    private String name;
    private Employee manager;
    private long memberCount;

    public TeamResponseDto(String name, Employee manager, long memberCount) {
        this.name = name;
        this.manager = manager;
        this.memberCount = memberCount;
    }

    public String getName() {
        return name;
    }

    public Employee getManager() {
        return manager;
    }

    public long getMemberCount() {
        return memberCount;
    }
}
