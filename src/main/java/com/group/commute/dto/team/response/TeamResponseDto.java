package com.group.commute.dto.team.response;

import com.group.commute.domain.employee.Employee;

public class TeamResponseDto {

    private String name;
    private String manager;
    private long memberCount;

    public TeamResponseDto(String name, String manager, long memberCount) {
        this.name = name;
        this.manager = manager;
        this.memberCount = memberCount;
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
}
