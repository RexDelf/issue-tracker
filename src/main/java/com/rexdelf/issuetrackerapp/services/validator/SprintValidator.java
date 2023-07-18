package com.rexdelf.issuetrackerapp.services.validator;

import com.rexdelf.issuetrackerapp.models.Sprint;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SprintValidator {

  public boolean isCompleted(LocalDate endDate){
    return endDate.isBefore(LocalDate.now());
  }

  public boolean validateStartDate(LocalDate startDate) {
    return !startDate.isBefore(LocalDate.now());
  }

  public boolean isAfterStartDate(LocalDate startDate, LocalDate endDate){
    return endDate.isAfter(startDate);
  }

  public boolean isOverlapping(List<Sprint> sprints, LocalDate date) {
    return sprints.stream()
        .noneMatch(sprint -> sprint.getEndDate().isAfter(date));
  }
}
