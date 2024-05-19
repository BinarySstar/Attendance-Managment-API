package com.group.commute.service;

import com.group.commute.domain.team.Team;
import com.group.commute.domain.team.TeamRepository;
import com.group.commute.dto.team.TeamResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional(readOnly = true)
    public List<TeamResponseDto> findAllTeam() {
        List<Team> result = teamRepository.findAll();
        return result.stream()
                .map(Team::toDto)
                .toList();
    }
}
