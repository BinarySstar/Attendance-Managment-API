package com.group.commute.dto.overtime.response;

public class OverTimeResponseDto {
    private Long id;
    private String name;
    private long overtimeMinutes = 0;

    public OverTimeResponseDto(Long id, String name) {
        this.id = id;

        StringBuilder sb = new StringBuilder(name);
        sb.setCharAt(1, '*');
        this.name = sb.toString();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getOvertimeMinutes() {
        return overtimeMinutes;
    }

    public void setOvertimeMinutes(long overtimeMinutes) {
        this.overtimeMinutes += overtimeMinutes;
    }
}
