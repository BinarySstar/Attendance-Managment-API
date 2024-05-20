package com.group.commute.controller;

import com.group.commute.dto.team.request.TeamCreateRequestDto;
import com.group.commute.dto.team.response.TeamResponseDto;
import com.group.commute.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamResponseDto> findAllTeam(){
        return teamService.findAllTeam();
    }

    @PostMapping
    public void createTeam(@RequestBody TeamCreateRequestDto requestDto) {
        teamService.createTeam(requestDto);
    }
}
