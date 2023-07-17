package com.rexdelf.issuetrackerapp.mapper;

import com.rexdelf.issuetrackerapp.dto.TicketDto;
import com.rexdelf.issuetrackerapp.dto.TicketPatchDto;
import com.rexdelf.issuetrackerapp.dto.TicketPostDto;
import com.rexdelf.issuetrackerapp.dto.TicketPostResponseDto;
import com.rexdelf.issuetrackerapp.models.Ticket;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TicketMapper {

  TicketDto ticketToTicketDto(Ticket ticket);
  @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Ticket patchTicket(@MappingTarget Ticket targetTicket, TicketPatchDto ticketPatchDto);

  Ticket ticketPostDtoToTicket(TicketPostDto ticketPostDto);

  TicketPostResponseDto ticketToTicketPostResponseDto(Ticket ticket);

  default OffsetDateTime mapToOffsetDateTime(LocalDateTime dateTime){
    return OffsetDateTime.of(dateTime, ZoneOffset.UTC);
  }
}
