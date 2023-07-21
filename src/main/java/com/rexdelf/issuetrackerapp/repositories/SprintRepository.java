package com.rexdelf.issuetrackerapp.repositories;

import com.rexdelf.issuetrackerapp.models.Sprint;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
  @Query("SELECT s FROM Sprint s WHERE :date BETWEEN s.startDate AND s.endDate")
  List<Sprint> findByDate(@Param("date") LocalDate date);
}
