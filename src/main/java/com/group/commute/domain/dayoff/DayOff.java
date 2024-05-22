package com.group.commute.domain.dayoff;

import com.group.commute.domain.employee.Employee;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class DayOff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private LocalDate date;

    public DayOff(Employee employee, LocalDate date) {
        this.employee = employee;
        this.date = date;
    }

    protected DayOff() {

    }

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getDate() {
        return date;
    }
}
