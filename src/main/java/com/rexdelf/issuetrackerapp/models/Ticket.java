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

    @Column(nullable = false)
    private Long reporter_id;

    private Long assignee_id;

    @Column(nullable = false)
    private Long status_id;

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private Long priority_id;

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
