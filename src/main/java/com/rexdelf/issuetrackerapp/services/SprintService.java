package com.rexdelf.issuetrackerapp.services;

import com.rexdelf.issuetrackerapp.dto.SprintPostDto;
import com.rexdelf.issuetrackerapp.dto.SprintPatchDto;
import com.rexdelf.issuetrackerapp.models.Sprint;
import java.time.LocalDate;
import java.util.List;

public interface SprintService {

  Sprint findById(Long id);

  List<Sprint> findAll(LocalDate startDate, LocalDate endDate);

  Sprint save(SprintPostDto patch);

  void deleteById(Long id);

  Sprint applyPatch(SprintPatchDto patch, Long id);
}
