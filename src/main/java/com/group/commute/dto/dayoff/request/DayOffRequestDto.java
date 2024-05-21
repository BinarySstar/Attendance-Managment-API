package com.group.commute.dto.dayoff.request;

import java.time.LocalDate;

public class DayOffRequestDto {
    private Long employeeId;

    private LocalDate date;

    public DayOffRequestDto(Long employeeId, LocalDate date) {
        this.employeeId = employeeId;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}
