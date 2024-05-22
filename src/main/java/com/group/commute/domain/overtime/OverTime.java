package com.group.commute.domain.overtime;

import com.group.commute.domain.employee.Employee;
import com.group.commute.dto.overtime.response.OverTimeResponseDto;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class OverTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    private LocalDate date;

    private long overtimeMinutes;

    public OverTime(Employee employee, long overtimeMinutes, LocalDate date) {
        this.employee = employee;
        this.overtimeMinutes = overtimeMinutes;
        this.date = date;
    }

    protected OverTime() {

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

    public OverTimeResponseDto toDto(){
        return new OverTimeResponseDto(id, employee.getName());
    }
}
