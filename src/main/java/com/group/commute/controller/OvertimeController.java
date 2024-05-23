package com.group.commute.controller;

import com.group.commute.dto.overtime.response.OvertimeResponseDto;
import com.group.commute.service.OvertimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/overtime")
public class OvertimeController {

    private final OvertimeService overTimeService;

    public OvertimeController(OvertimeService overTimeService) {
        this.overTimeService = overTimeService;
    }

    @GetMapping
    public List<OvertimeResponseDto> getMonthlyOverTime(@RequestParam String yearMonth) {
        return overTimeService.getMonthlyOverTime(yearMonth);
    }
}
