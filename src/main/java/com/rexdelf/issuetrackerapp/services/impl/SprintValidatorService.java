package com.rexdelf.issuetrackerapp.services.impl;

import com.rexdelf.issuetrackerapp.exceptions.InvalidDateException;
import com.rexdelf.issuetrackerapp.exceptions.ModificationNotAllowedException;
import com.rexdelf.issuetrackerapp.models.Sprint;
import com.rexdelf.issuetrackerapp.models.SprintStatus;
import com.rexdelf.issuetrackerapp.repositories.SprintRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SprintValidatorService {
  private final SprintValidator sprintValidator;
  private final SprintRepository sprintRepository;
  private final DateValidatorService dateValidatorService;

  public void checkIfModifiable(Sprint sprint){
    if(sprint.getStatus() == SprintStatus.COMPLETED){
      throw new ModificationNotAllowedException("You can't edit completed sprints");
    }

    if(sprint.getStatus() == SprintStatus.ACTIVE){
      throw new ModificationNotAllowedException("You can't change the start date of active sprints");
    }
  }

  public void checkIfDeletable(Sprint sprint){
    if(sprint.getStatus() != SprintStatus.SCHEDULED){
      throw new ModificationNotAllowedException("You can't delete completed or active sprints");
    }
  }

  public void validateDates(LocalDate startDate, LocalDate endDate){
    if(!sprintValidator.isOverlappingWithExistingSprint(sprintRepository.findAll(), startDate)){
      throw new InvalidDateException("Sprint dates can't overlap with existing sprints");
    }

    dateValidatorService.notInThePast(startDate);

    dateValidatorService.endDateIsAfterStartDate(startDate, endDate);
  }

}
