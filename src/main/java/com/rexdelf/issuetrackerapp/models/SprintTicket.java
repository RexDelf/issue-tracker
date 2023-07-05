package com.rexdelf.issuetrackerapp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sprint_ticket")
public class SprintTicket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long sprint_id;

  @Column(nullable = false)
  private Long ticket_id;

  @ManyToOne
  @JoinColumn(name = "sprint_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Sprint sprint;

  @ManyToOne
  @JoinColumn(name = "ticket_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Ticket ticket;
}
