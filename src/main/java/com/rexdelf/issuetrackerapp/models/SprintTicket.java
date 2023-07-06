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

  @Column(name = "sprint_id", nullable = false)
  private Long sprintId;

  @Column(name = "ticket_id", nullable = false)
  private Long ticketId;

  @ManyToOne
  @JoinColumn(name = "sprint_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Sprint sprint;

  @ManyToOne
  @JoinColumn(name = "ticket_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Ticket ticket;
}
