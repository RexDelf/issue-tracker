package com.rexdelf.issuetrackerapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketDTO {
    private Long id;
    private String title;
    private String description;
    private String reporter_name;
    private String reporter_surname;
    private String assignee_name;
    private String assignee_surname;
    private String status;
    private LocalDateTime created_at;
    private String priority;
}
