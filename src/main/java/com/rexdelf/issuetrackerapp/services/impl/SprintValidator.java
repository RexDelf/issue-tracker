package com.rexdelf.issuetrackerapp.services.impl;

import com.rexdelf.issuetrackerapp.models.Sprint;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
class SprintValidator {

  public boolean isOverlappingWithExistingSprint(List<Sprint> sprints, LocalDate date) {
    return sprints.stream()
        .noneMatch(sprint -> sprint.getEndDate().isAfter(date));
  }
}
