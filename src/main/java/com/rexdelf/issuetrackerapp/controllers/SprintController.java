package com.rexdelf.issuetrackerapp.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import com.rexdelf.issuetrackerapp.dto.SprintDto;
import com.rexdelf.issuetrackerapp.dto.SprintPostDto;
import com.rexdelf.issuetrackerapp.dto.SprintPatchDto;
import com.rexdelf.issuetrackerapp.dto.SprintsDto;
import com.rexdelf.issuetrackerapp.mapper.SprintMapper;
import com.rexdelf.issuetrackerapp.models.Sprint;
import com.rexdelf.issuetrackerapp.services.SprintService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class SprintController implements SprintsApi{

  private final SprintService sprintService;

  private final SprintMapper mapper;

  @Override
  public ResponseEntity<SprintsDto> getAllSprints(@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
                                                  @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate endDate){
    List<Sprint> sprints = sprintService.findAll(startDate, endDate);

    SprintsDto sprintsDto = new SprintsDto();

    sprintsDto.setItems(sprints.stream()
        .map(mapper::sprintToSprintDto)
        .toList());

    return new ResponseEntity<>(sprintsDto, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<SprintDto> getSprint(@PathVariable Long id) {
    Sprint sprint = sprintService.findById(id);

    return new ResponseEntity<>(mapper.sprintToSprintDto(sprint), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> deleteSprint(@PathVariable Long id){
    sprintService.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<SprintDto> createSprint(@RequestBody SprintPostDto sprintPostDto) {
    Sprint savedSprint = sprintService.save(sprintPostDto);

    String location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedSprint.getId())
        .toUriString();

    return ResponseEntity.status(CREATED)
        .header(HttpHeaders.LOCATION, location)
        .body(mapper.sprintToSprintDto(savedSprint));
  }

  @Override
  public ResponseEntity<SprintDto> updateSprint(@PathVariable Long id, @RequestBody SprintPatchDto patch) {

    Sprint patchedSprint = sprintService.applyPatch(patch, id);

    return new ResponseEntity<>(mapper.sprintToSprintDto(patchedSprint), HttpStatus.OK);
  }
}
