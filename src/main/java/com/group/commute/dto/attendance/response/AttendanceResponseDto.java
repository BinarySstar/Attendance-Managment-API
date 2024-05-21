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

    public void add(LocalDate date, long minutesWorked) {
        this.detail.add(new WorkDay(date, minutesWorked));
        this.sum += minutesWorked;
    }
    public static class WorkDay {
        private LocalDate date;
        private long minutesWorked;

        public WorkDay(LocalDate date, long minutesWorked) {
            this.date = date;
            this.minutesWorked = minutesWorked;
        }

        public LocalDate getDate() {
            return date;
        }

        public long getMinutesWorked() {
            return minutesWorked;
        }
    }
}
