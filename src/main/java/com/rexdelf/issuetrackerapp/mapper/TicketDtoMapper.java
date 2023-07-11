package com.rexdelf.issuetrackerapp.mapper;

import com.rexdelf.issuetrackerapp.dto.TicketDto;
import com.rexdelf.issuetrackerapp.models.Ticket;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketDtoMapper {
  @Mapping(target = "priority", source = "priority.name")
  @Mapping(target = "status", source = "status.name")
  @Mapping(target = "assigneeName", source = "assignee.name")
  @Mapping(target = "assigneeSurname", source = "assignee.surname")
  @Mapping(target = "reporterName", source = "reporter.name")
  @Mapping(target = "reporterSurname", source = "reporter.surname")
  TicketDto toTicketDto(Ticket ticket);

  default OffsetDateTime map(LocalDateTime dateTime){
    return OffsetDateTime.of(dateTime, ZoneOffset.UTC);
  }
}
