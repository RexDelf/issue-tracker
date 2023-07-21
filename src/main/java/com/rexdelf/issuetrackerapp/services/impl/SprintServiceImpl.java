package com.rexdelf.issuetrackerapp.services.impl;

import com.rexdelf.issuetrackerapp.dto.SprintPostDto;
import com.rexdelf.issuetrackerapp.dto.SprintPatchDto;
import com.rexdelf.issuetrackerapp.exceptions.NotFoundException;
import com.rexdelf.issuetrackerapp.mapper.SprintMapper;
import com.rexdelf.issuetrackerapp.models.Sprint;
import com.rexdelf.issuetrackerapp.repositories.SprintRepository;
import com.rexdelf.issuetrackerapp.services.SprintService;
import java.time.LocalDate;
import java.util.List;
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

  private final SprintValidatorService sprintValidatorService;


  @Override
  public List<Sprint> findAll(LocalDate date){
    if (date == null) {
      return sprintRepository.findAll();
    } else {
      return sprintRepository.findByDate(date);
    }
  }

  @Override
  public void deleteById(Long id){
    Sprint sprint = findById(id);

    sprintValidatorService.checkIfDeletable(sprint);

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

    sprintValidatorService.checkIfModifiable(targetSprint, patch);

    Sprint patchedSprint = mapper.patchSprint(targetSprint, patch);

    sprintValidatorService.validateDates(patchedSprint.getStartDate(), patchedSprint.getEndDate());

    return sprintRepository.save(patchedSprint);
  }

  @Override
  public Sprint save(SprintPostDto sprintPostDto){
    if(sprintPostDto.getEndDate() == null){
      sprintPostDto.setEndDate(sprintPostDto.getStartDate().plusDays(defaultSprintLength));
    }

    sprintValidatorService.validateDates(sprintPostDto.getStartDate(), sprintPostDto.getEndDate());

    return sprintRepository.save(mapper.sprintPostDtoToSprint(sprintPostDto));
  }

}