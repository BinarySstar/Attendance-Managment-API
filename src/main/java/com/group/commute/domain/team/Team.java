package com.group.commute.domain.team;

import com.group.commute.dto.team.TeamResponseDto;
import jakarta.persistence.*;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 팀 이름
    @Column(nullable = false)
    private String name;

    // 매니저 이름
    @Column
    private String manager;

    // 팀 멤버 인원 수
    @Column
    private long memberCount;

    public Team(String name, String manager, long memberCount) {
        this.name = name;
        this.manager = manager;
        this.memberCount = memberCount;
    }

    protected Team() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManager() {
        return manager;
    }

    public long getMemberCount() {
        return memberCount;
    }

    public void addMemberCount(int value){
        this.memberCount += value;
    }

    public TeamResponseDto toDto() {
        return new TeamResponseDto(this.name, this.manager, this.memberCount);
    }
}
