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

    public void add(LocalDate date, long workingMinutes, boolean usingDayOff) {
        this.detail.add(new WorkDay(date, workingMinutes, usingDayOff));
        this.sum += workingMinutes;
    }
    public static class WorkDay {
        private LocalDate date;
        private long workingMinutes;

        private boolean usingDayOff;

        public WorkDay(LocalDate date, long workingMinutes, boolean usingDayOff) {
            this.date = date;
            this.workingMinutes = workingMinutes;
            this.usingDayOff = usingDayOff;
        }

        public LocalDate getDate() {
            return date;
        }

        public long getWorkingMinutes() {
            return workingMinutes;
        }

        public boolean isUsingDayOff() {
            return usingDayOff;
        }
    }
}
