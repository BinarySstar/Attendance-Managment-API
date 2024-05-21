package com.group.commute.controller;

import com.group.commute.dto.attendance.response.AttendanceResponseDto;
import com.group.commute.service.AttendanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/workhours")
    public AttendanceResponseDto getWorkHours(@RequestParam Long employeeId,
                                              @RequestParam String yearMonth) {
        return attendanceService.getMonthlyWorkHours(employeeId, yearMonth);
    }

    @PostMapping("/checkin")
    public void checkIn(@RequestParam Long employeeId) {
        attendanceService.checkIn(employeeId);
    }

    @PostMapping("/checkout")
    public void checkOut(@RequestParam Long employeeId) {
        attendanceService.checkOut(employeeId);
    }
}
