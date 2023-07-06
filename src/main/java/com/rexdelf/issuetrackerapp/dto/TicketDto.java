package com.rexdelf.issuetrackerapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketDto {

  private Long id;
  private String title;
  private String description;
  private String reporterName;
  private String reporterSurname;
  private String assigneeName;
  private String assigneeSurname;
  private String status;
  private LocalDateTime createdAt;
  private String priority;
}
