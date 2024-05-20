package com.group.commute.domain.employee;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.group.commute.domain.team.Team;
import com.group.commute.dto.employee.response.EmployeeResponseDto;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDate birthday;

    private LocalDate workStartDate;

    public Employee(String name, Team team, Role role, LocalDate birthday, LocalDate workStartDate) {
        this.name = name;
        this.team = team;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
    }

    protected Employee() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public Role getRole() {
        return role;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getWorkStartDate() {
        return workStartDate;
    }

    public EmployeeResponseDto toDto() {
        return new EmployeeResponseDto(this.name, this.team, this.role, this.birthday, this.workStartDate);
    }
}
