package com.group.commute.controller;

import com.group.commute.dto.overtime.response.OverTimeResponseDto;
import com.group.commute.service.OverTimeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/overtime")
public class OverTimeController {

    private final OverTimeService overTimeService;

    public OverTimeController(OverTimeService overTimeService) {
        this.overTimeService = overTimeService;
    }

    public List<OverTimeResponseDto> getMonthlyOverTime(@RequestParam String yearMonth) {
        return overTimeService.getMonthlyOverTime(yearMonth);
    }
}
