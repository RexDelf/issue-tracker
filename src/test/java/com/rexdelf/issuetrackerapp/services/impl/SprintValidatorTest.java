package com.rexdelf.issuetrackerapp.services.impl;

import com.rexdelf.issuetrackerapp.models.Sprint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SprintValidatorTest {

  private SprintValidator validator;

  @Mock
  private Sprint mockSprint1;

  @Mock
  private Sprint mockSprint2;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    validator = new SprintValidator();
  }

  @Test
  void testIsInThePast() {
    LocalDate dateInThePast = LocalDate.now().minusDays(1);
    LocalDate dateInTheFuture = LocalDate.now().plusDays(1);

    assertTrue(validator.isInThePast(dateInThePast));
    assertFalse(validator.isInThePast(dateInTheFuture));
  }

  @Test
  void testIsScheduled() {
    LocalDate dateInThePast = LocalDate.now().minusDays(1);
    LocalDate dateInTheFuture = LocalDate.now().plusDays(1);

    assertFalse(validator.isScheduled(dateInThePast));
    assertTrue(validator.isScheduled(dateInTheFuture));
  }

  @Test
  void testIsAfterStartDate() {
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now().plusDays(1);

    assertTrue(validator.isAfterStartDate(startDate, endDate));
    assertFalse(validator.isAfterStartDate(endDate, startDate));
  }

  @Test
  void testIsOverlapping() {
    LocalDate date = LocalDate.now().plusDays(1);
    when(mockSprint1.getEndDate()).thenReturn(LocalDate.now());
    when(mockSprint2.getEndDate()).thenReturn(LocalDate.now().plusDays(2));

    List<Sprint> sprints = Arrays.asList(mockSprint1, mockSprint2);
    assertFalse(validator.isOverlapping(sprints, date));
  }
}