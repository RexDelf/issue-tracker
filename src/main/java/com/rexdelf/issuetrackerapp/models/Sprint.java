package com.rexdelf.issuetrackerapp.models;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Sprint {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private LocalDate startDate;

  @Column(nullable = false)
  private LocalDate endDate;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String goals;

  public SprintStatus getStatus() {
    LocalDate now = LocalDate.now();

    if (now.isBefore(this.startDate)) {
      return SprintStatus.SCHEDULED;
    }
    else if (now.isAfter(this.endDate)) {
      return SprintStatus.COMPLETED;
    }
    else {
      return SprintStatus.ACTIVE;
    }
  }
}
