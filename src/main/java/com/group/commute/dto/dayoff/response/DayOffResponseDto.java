package com.group.commute.dto.dayoff.response;

public class DayOffResponseDto {
    private Long employeeId;
    private long remainigDayOff;

    public DayOffResponseDto(Long employeeId, long remainigDayOff) {
        this.employeeId = employeeId;
        this.remainigDayOff = remainigDayOff;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public long getRemainigDayOff() {
        return remainigDayOff;
    }
}
