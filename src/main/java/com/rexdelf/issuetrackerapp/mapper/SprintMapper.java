package com.rexdelf.issuetrackerapp.mapper;

import com.rexdelf.issuetrackerapp.dto.SprintDto;
import com.rexdelf.issuetrackerapp.dto.SprintPatchDto;
import com.rexdelf.issuetrackerapp.dto.SprintPostDto;
import com.rexdelf.issuetrackerapp.models.Sprint;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {BaseMapper.class})
public interface SprintMapper{

  Sprint sprintPostDtoToSprint(SprintPostDto patch);

  @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Sprint patchSprint(@MappingTarget Sprint targetSprint, SprintPatchDto patch);
  SprintDto sprintToSprintDto(Sprint sprint);

}
