package com.group.commute.domain.overtime;

import com.group.commute.domain.employee.Employee;
import com.group.commute.dto.overtime.response.OvertimeResponseDto;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Overtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    private LocalDate date;

    private long overtimeMinutes;

    public Overtime(Employee employee, long overtimeMinutes, LocalDate date) {
        this.employee = employee;
        this.overtimeMinutes = overtimeMinutes;
        this.date = date;
    }

    protected Overtime() {

    }

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public long getOvertimeMinutes() {
        return overtimeMinutes;
    }

    public LocalDate getDate() {
        return date;
    }

    public OvertimeResponseDto toDto(){
        return new OvertimeResponseDto(id, employee.getName());
    }
}
