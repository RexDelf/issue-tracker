package com.rexdelf.issuetrackerapp.services.impl;

import com.rexdelf.issuetrackerapp.models.Sprint;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
class SprintValidator {

  public boolean isInThePast(LocalDate date) {
    return date.isBefore(LocalDate.now());
  }

  public boolean isScheduled(LocalDate startDate){
    return startDate.isAfter(LocalDate.now());
  }

  public boolean isAfterStartDate(LocalDate startDate, LocalDate endDate){
    return endDate.isAfter(startDate);
  }

  public boolean isOverlapping(List<Sprint> sprints, LocalDate date) {
    return sprints.stream()
        .noneMatch(sprint -> sprint.getEndDate().isAfter(date));
  }
}
