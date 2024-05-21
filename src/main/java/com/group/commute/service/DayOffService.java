package com.group.commute.service;

import com.group.commute.domain.dayoff.DayOff;
import com.group.commute.domain.dayoff.DayOffRepository;
import com.group.commute.domain.employee.Employee;
import com.group.commute.domain.employee.EmployeeRepository;
import com.group.commute.dto.dayoff.request.DayOffRequestDto;
import com.group.commute.dto.dayoff.response.DayOffResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class DayOffService {
    private static final int MAX_DAYOFF = 15;
    private static final int MAX_NEWCOMER_DAYOFF = 11;
    private final DayOffRepository dayOffRepository;
    private final EmployeeRepository employeeRepository;

    public DayOffService(DayOffRepository dayOffRepository, EmployeeRepository employeeRepository) {
        this.dayOffRepository = dayOffRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void applyDayOff(DayOffRequestDto requestDto) {
        Employee employee = employeeRepository.findById(requestDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("직원이 존재하지 않습니다."));

        LocalDate today = LocalDate.now();
        int beforeDay = employee.getTeam().getDayOffApplyDay();

        validateDayOffRequest(requestDto, today, beforeDay, employee);
        
        DayOff dayOff = new DayOff(employee, today);
        dayOffRepository.save(dayOff);
    }

    private void validateDayOffRequest(DayOffRequestDto requestDto, LocalDate today, int beforeDay, Employee employee) {
        if (dayOffRepository.existsByIdAndDate(requestDto.getEmployeeId(), today)) {
            throw new RuntimeException("이미 신청된 연차입니다.");
        }
        if (today.plusDays(beforeDay).isAfter(requestDto.getDate())) {
            throw new RuntimeException("연차는 최소 " + beforeDay + "일 전에 신청해야 합니다.");
        }
        if(validateRemainingDayOffs(employee)) {
            throw new RuntimeException("사용할 수 있는 연차를 초과하였습니다.");
        }
    }

    private boolean validateRemainingDayOffs(Employee employee) {
        long usedDayOff = dayOffRepository.countByEmployee(employee);
        long maxDayOff = getMaxDayOffs(employee);
        return maxDayOff - usedDayOff <= 0;
    }

    @Transactional
    public DayOffResponseDto getRemainingDayOff(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("직원이 존재하지 않습니다."));

        long usedDayOffs = dayOffRepository.countByEmployee(employee);
        long maxDayOffs = getMaxDayOffs(employee);
        long remainingDayOffs = maxDayOffs - usedDayOffs;

        return new DayOffResponseDto(employeeId, remainingDayOffs);
    }

    private long getMaxDayOffs(Employee employee) {
        return (employee.getWorkStartDate().getYear() == LocalDate.now().getYear())
                ? MAX_NEWCOMER_DAYOFF : MAX_DAYOFF;
    }
}
