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

  public void validateDates(LocalDate startDate, LocalDate endDate){
    if(dateValidator.isInThePast(startDate)){
      throw new InvalidDateException("Start date cannot be in the past");
    }

    if(!dateValidator.isAfterStartDate(startDate, endDate)){
      throw new InvalidDateException("End date must be after start date");
    }
  }
}
