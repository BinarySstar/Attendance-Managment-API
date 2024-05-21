package com.group.commute.domain.dayoff;

import com.group.commute.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Long> {
    List<DayOff> findAllById(Long employeeId);

    boolean existsByIdAndDate(Long employeeId, LocalDate today);

    long countByEmployee(Employee employee);
}
