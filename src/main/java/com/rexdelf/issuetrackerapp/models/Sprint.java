package com.rexdelf.issuetrackerapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
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

  @JsonManagedReference
  @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<SprintTicket> sprintTickets;

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
