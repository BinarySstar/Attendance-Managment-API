package com.group.commute.service;

import com.group.commute.domain.attendance.Attendance;
import com.group.commute.domain.attendance.AttendanceRepository;
import com.group.commute.domain.employee.Employee;
import com.group.commute.domain.employee.EmployeeRepository;
import com.group.commute.dto.attendance.response.AttendanceResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public AttendanceResponseDto getMonthlyWorkHours(Long employeeId, String yearMonth) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);

        YearMonth ym = YearMonth.parse(yearMonth);
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();

        List<Attendance> attendances = attendanceRepository.findByEmployeeAndDateBetween(employee, startDate, endDate);

        return calculateMinutesWorked(attendances);
    }

    private AttendanceResponseDto calculateMinutesWorked(List<Attendance> attendances) {
        AttendanceResponseDto responseDto = new AttendanceResponseDto();

        attendances.forEach(attendance -> {
            if(attendance.getCheckInTime() != null && attendance.getCheckOutTime() != null) {
                Duration duration = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime());
                long workingMinutes = duration.toMinutes();
                responseDto.add(attendance.getDate(), workingMinutes);
            }
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
        attendanceRepository.save(attendance);
    }
}
