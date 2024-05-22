package com.group.commute.service;

import com.group.commute.domain.overtime.OverTime;
import com.group.commute.domain.overtime.OverTimeRepository;
import com.group.commute.dto.overtime.response.OverTimeResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class OverTimeService {
    private final OverTimeRepository overTimeRepository;

    public OverTimeService(OverTimeRepository overTimeRepository) {
        this.overTimeRepository = overTimeRepository;
    }

    @Transactional
    public List<OverTimeResponseDto> getMonthlyOverTime(String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth);
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();

        List<OverTime> overTimes = overTimeRepository.findByDateBetween(startDate, endDate);
        return overTimes.stream().map(OverTime::toDto).toList();
    }
}
