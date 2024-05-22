package com.group.commute.domain.dayoff;

import com.group.commute.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Long> {

    boolean existsByIdAndDate(Long employeeId, LocalDate today);

    long countByEmployee(Employee employee);
}
