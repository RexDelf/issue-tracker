package com.rexdelf.issuetrackerapp.services.impl;

import com.rexdelf.issuetrackerapp.exceptions.InvalidDateException;
import com.rexdelf.issuetrackerapp.util.DateValidator;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class DateValidatorService {

  private final DateValidator dateValidator;

  public void notInThePast(LocalDate date) {
    if (dateValidator.isInThePast(date)) {
      throw new InvalidDateException("Date cannot be in the past");
    }
  }

  public void validatePeriod(LocalDate startDate, LocalDate endDate){
      if (!dateValidator.isAfterStartDate(startDate, endDate)) {
        throw new InvalidDateException("End date must be after start date");
      }
    }
}
