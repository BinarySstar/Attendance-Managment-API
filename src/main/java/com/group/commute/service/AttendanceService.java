package com.group.commute.service;

import com.group.commute.domain.attendance.Attendance;
import com.group.commute.domain.attendance.AttendanceRepository;
import com.group.commute.domain.dayoff.DayOff;
import com.group.commute.domain.dayoff.DayOffRepository;
import com.group.commute.domain.employee.Employee;
import com.group.commute.domain.employee.EmployeeRepository;
import com.group.commute.domain.overtime.HolidayManager;
import com.group.commute.domain.overtime.Overtime;
import com.group.commute.domain.overtime.OvertimeRepository;
import com.group.commute.dto.attendance.response.AttendanceResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final OvertimeRepository overtimeRepository;

    public AttendanceService(AttendanceRepository attendanceRepository,
                             EmployeeRepository employeeRepository,
                             OvertimeRepository overtimeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
        this.overtimeRepository = overtimeRepository;
    }

    @Transactional
    public AttendanceResponseDto getMonthlyWorkHours(Long employeeId, String yearMonth) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);

        YearMonth ym = YearMonth.parse(yearMonth);
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();

        List<Attendance> attendances = attendanceRepository.findByEmployeeAndDateBetween(employee, startDate, endDate);

        return calculateWorkingMinutes(attendances);
    }

    private AttendanceResponseDto calculateWorkingMinutes(List<Attendance> attendances) {
        AttendanceResponseDto responseDto = new AttendanceResponseDto();

        attendances.forEach(attendance -> {
            boolean isUsingDayOff = attendance.isUsingDayOff();
            long workingMinutes = 0;

            if(!isUsingDayOff && attendance.getCheckInTime() != null && attendance.getCheckOutTime() != null) {
                Duration duration = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime());
                workingMinutes = duration.toMinutes();
            }
            responseDto.add(attendance.getDate(), workingMinutes, isUsingDayOff);
        });
        return responseDto;
    }

    @Transactional
    public void checkIn(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 직원입니다."));

        attendanceRepository.findByEmployeeAndDate(employee, LocalDate.now())
                .ifPresent(attendance -> {
                    if(attendance.getCheckInTime() != null && attendance.getCheckOutTime() == null) {
                        throw new RuntimeException("이미 출근한 직원입니다.");
                    }
                    if(attendance.getCheckOutTime() != null) {
                        throw new RuntimeException("이미 퇴근한 직원입니다.");
                    }
                    if(attendance.isUsingDayOff()) {
                        throw new RuntimeException("금일 연차를 사용한 직원입니다.");
                    }
                });

        Attendance attendance = new Attendance(employee, LocalDate.now(), LocalTime.now());
        attendanceRepository.save(attendance);
    }

    @Transactional
    public void checkOut(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("직원을 찾지 못하였습니다!"));
        Attendance attendance = attendanceRepository.findByEmployeeAndDate(employee, LocalDate.now())
                .stream().findFirst().orElseThrow(() -> new RuntimeException("출근이 찍혀있지 않습니다."));
        attendance.setCheckOutTime(LocalTime.now());

        calculateOvertimeMinutes(attendance);
        
        attendanceRepository.save(attendance);
    }

    private void calculateOvertimeMinutes(Attendance attendance) {
        LocalDate date = attendance.getDate();
        if(isWeekendOrHoliday(date)) {
            Duration duration = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime());
            long overtimeMinutes = duration.toMinutes();

            Overtime overtime = new Overtime(attendance.getEmployee(), overtimeMinutes, date);
            overtimeRepository.save(overtime);
        }
    }

    private boolean isWeekendOrHoliday(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY ||
                HolidayManager.isHoliday(date);
    }
}
