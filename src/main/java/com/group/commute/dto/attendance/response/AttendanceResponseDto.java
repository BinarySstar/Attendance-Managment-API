package com.group.commute.dto.attendance.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceResponseDto {
    private List<WorkDay> detail = new ArrayList<>();
    private long sum = 0;

    public List<WorkDay> getDetail() {
        return detail;
    }

    public long getSum() {
        return sum;
    }

    public void add(LocalDate date, long workingMinutes) {
        this.detail.add(new WorkDay(date, workingMinutes));
        this.sum += workingMinutes;
    }
    public static class WorkDay {
        private LocalDate date;
        private long workingMinutes;

        public WorkDay(LocalDate date, long workingMinutes) {
            this.date = date;
            this.workingMinutes = workingMinutes;
        }

        public LocalDate getDate() {
            return date;
        }

        public long getWorkingMinutes() {
            return workingMinutes;
        }
    }
}
