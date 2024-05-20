package com.group.commute.service;

import com.group.commute.domain.employee.EmployeeRepository;
import com.group.commute.domain.employee.Role;
import com.group.commute.domain.team.Team;
import com.group.commute.domain.team.TeamRepository;
import com.group.commute.dto.team.request.TeamCreateRequestDto;
import com.group.commute.dto.team.response.TeamResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    public TeamService(TeamRepository teamRepository, EmployeeRepository employeeRepository) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public List<TeamResponseDto> findAllTeam() {
        List<Team> result = teamRepository.findAll();
        return result.stream()
                .map(Team::toDto)
                .toList();
    }

    @Transactional
    public void createTeam(TeamCreateRequestDto requestDto) {
        Team team = requestDto.toEntity();
        teamRepository.save(team);

        employeeRepository.findByTeamAndRole(team, Role.MANAGER)
                        .ifPresent(team::setManager);
        long count = employeeRepository.findByTeam(team)
                        .stream().count();
        team.setMemberCount(count);
        
        teamRepository.save(team);
    }
}
