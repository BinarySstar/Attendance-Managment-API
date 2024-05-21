package com.group.commute.controller;

import com.group.commute.dto.dayoff.request.DayOffRequestDto;
import com.group.commute.dto.dayoff.response.DayOffResponseDto;
import com.group.commute.service.DayOffService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dayoff")
public class DayOffController {
    private final DayOffService dayOffService;

    public DayOffController(DayOffService dayOffService) {
        this.dayOffService = dayOffService;
    }

    @PostMapping("/apply")
    public void applyDayOff(@RequestBody DayOffRequestDto requestDto){
        dayOffService.applyDayOff(requestDto);
    }

    @GetMapping
    public DayOffResponseDto getRemainigDayOff(@RequestParam Long employeeId) {
        return dayOffService.getRemainingDayOff(employeeId);
    }
}
