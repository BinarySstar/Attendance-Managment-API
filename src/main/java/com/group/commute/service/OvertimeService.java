package com.group.commute.service;

import com.group.commute.domain.overtime.Overtime;
import com.group.commute.domain.overtime.OvertimeRepository;
import com.group.commute.dto.overtime.response.OvertimeResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class OvertimeService {
    private final OvertimeRepository overtimeRepository;

    public OvertimeService(OvertimeRepository overtimeRepository) {
        this.overtimeRepository = overtimeRepository;
    }

    @Transactional
    public List<OvertimeResponseDto> getMonthlyOverTime(String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth);
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();

        List<Overtime> overtimes = overtimeRepository.findByDateBetween(startDate, endDate);
        return overtimes.stream()
                .map(Overtime::toDto).toList();
    }
}
