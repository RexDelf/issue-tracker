package com.rexdelf.issuetrackerapp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(name = "reporter_id", nullable = false)
  private Long reporterId;

  @Column(name = "assignee_id")
  private Long assigneeId;

  @Column(name = "status_id", nullable = false)
  private Long statusId;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "priority_id", nullable = false)
  private Long priorityId;

  @ManyToOne
  @JoinColumn(name = "reporter_id", referencedColumnName = "id", insertable = false, updatable = false)
  private User reporter;

  @ManyToOne
  @JoinColumn(name = "assignee_id", referencedColumnName = "id", insertable = false, updatable = false)
  private User assignee;

  @ManyToOne
  @JoinColumn(name = "status_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Status status;

  @ManyToOne
  @JoinColumn(name = "priority_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Priority priority;
}
