package com.rexdelf.issuetrackerapp.repositories;

import com.rexdelf.issuetrackerapp.models.Sprint;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
  List<Sprint> findBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
