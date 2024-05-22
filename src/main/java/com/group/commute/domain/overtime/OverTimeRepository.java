package com.group.commute.domain.overtime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OverTimeRepository extends JpaRepository<OverTime, Long> {
    List<OverTime> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
