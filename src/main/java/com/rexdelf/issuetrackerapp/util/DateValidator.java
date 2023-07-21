package com.rexdelf.issuetrackerapp.util;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class DateValidator {
  public boolean isInThePast(LocalDate date) {
    return date.isBefore(LocalDate.now());
  }

  public boolean isAfterStartDate(LocalDate startDate, LocalDate endDate){
    return endDate.isAfter(startDate);
  }
}
