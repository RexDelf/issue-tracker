package com.rexdelf.issuetrackerapp.services.impl;

import com.rexdelf.issuetrackerapp.dto.SprintPostDto;
import com.rexdelf.issuetrackerapp.dto.SprintPatchDto;
import com.rexdelf.issuetrackerapp.exceptions.InvalidDateException;
import com.rexdelf.issuetrackerapp.exceptions.ModificationNotAllowedException;
import com.rexdelf.issuetrackerapp.exceptions.NotFoundException;
import com.rexdelf.issuetrackerapp.mapper.SprintMapper;
import com.rexdelf.issuetrackerapp.models.Sprint;
import com.rexdelf.issuetrackerapp.repositories.SprintRepository;
import com.rexdelf.issuetrackerapp.services.SprintService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService{

  @Value("${app.defaultSprintDays}")
  private Integer defaultSprintLength;

  private final SprintMapper mapper;

  private final SprintRepository sprintRepository;

  private final SprintValidator sprintValidator;

  @Override
  public List<Sprint> findAll(){
      return sprintRepository.findAll();
  }

  @Override
  public void deleteById(Long id){
    Sprint sprint = findById(id);

    if(!sprintValidator.isScheduled(sprint.getStartDate())){
      throw new ModificationNotAllowedException("You can't delete completed or active sprints");
    }

    sprintRepository.deleteById(id);
  }

  @Override
  public Sprint findById(Long id){
    return sprintRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Sprint not found for id: " + id));
  }
  @Override
  public Sprint applyPatch(SprintPatchDto patch, Long id){
    Sprint targetSprint = findById(id);

    if(sprintValidator.isInThePast(targetSprint.getEndDate())){
      throw new ModificationNotAllowedException("You can't edit completed sprints");
    }

    if(sprintValidator.isInThePast(patch.getStartDate()) && patch.getStartDate() != null){
      throw new ModificationNotAllowedException("You can't change the start date of active sprints");
    }

    LocalDate startDate = Optional.ofNullable(patch.getStartDate())
        .orElse(targetSprint.getStartDate());
    LocalDate endDate = Optional.ofNullable(patch.getEndDate())
        .orElse(targetSprint.getEndDate());

    validateDates(startDate, endDate);

    Sprint patchedSprint = mapper.patchSprint(targetSprint, patch);

    return sprintRepository.save(patchedSprint);
  }

  @Override
  public Sprint save(SprintPostDto sprintPostDto){
    if(sprintPostDto.getEndDate() == null){
      sprintPostDto.setEndDate(sprintPostDto.getStartDate().plusDays(defaultSprintLength));
    }

    validateDates(sprintPostDto.getStartDate(), sprintPostDto.getEndDate());

    return sprintRepository.save(mapper.sprintPostDtoToSprint(sprintPostDto));
  }

  private void validateDates(LocalDate startDate, LocalDate endDate){
    if(sprintValidator.isInThePast(startDate)){
      throw new InvalidDateException("Start date cannot be in the past");
    }

    if(!sprintValidator.isOverlapping(findAll(), startDate)){
      throw new InvalidDateException("Sprint dates can't overlap with existing sprints");
    }

    if(!sprintValidator.isAfterStartDate(startDate, endDate)){
      throw new InvalidDateException("End date must be after start date");
    }
  }
}
