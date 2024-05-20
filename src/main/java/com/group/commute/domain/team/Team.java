package com.group.commute.domain.team;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group.commute.domain.employee.Employee;
import com.group.commute.dto.team.response.TeamResponseDto;
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
    @JoinColumn(name = "employee_id")
    @OneToOne
    private Employee manager;

    // 팀 멤버 인원 수
    @Column
    private long memberCount = 0;

    public Team(String name, Employee manager) {
        this.name = name;
        this.manager = manager;
    }

    public Team(String name) {
        this.name = name;
    }
    protected Team() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Employee getManager() {
        return manager;
    }

    public long getMemberCount() {
        return memberCount;
    }

    public void addMemberCount(int value){
        this.memberCount += value;
    }

    public TeamResponseDto toDto() {
        String managerName = (this.manager == null) ? null : this.manager.getName();
        return new TeamResponseDto(this.name, managerName, this.memberCount);
    }

    public void setManager(Employee employee) {
        this.manager = employee;
    }

    public void setMemberCount(long memberCount) {
        this.memberCount = memberCount;
    }
}
