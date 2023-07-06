package com.rexdelf.issuetrackerapp.controllers;

import com.rexdelf.issuetrackerapp.dto.TicketDto;
import com.rexdelf.issuetrackerapp.models.Ticket;
import com.rexdelf.issuetrackerapp.services.TicketService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/tickets")
@RestController
@RequiredArgsConstructor
public class TicketController {

  @Autowired
  private ModelMapper modelMapper;
  private final TicketService ticketService;

  @GetMapping
  public ResponseEntity<List<TicketDto>> getAll() {
    modelMapper.addConverter(converter);

    List<Ticket> tickets = ticketService.findAll();

    List<TicketDto> ticketsDto = tickets.stream()
        .map(ticket -> modelMapper.map(ticket, TicketDto.class))
        .toList();

    return new ResponseEntity<>(ticketsDto, HttpStatus.OK);
  }

  Converter<Ticket, TicketDto> converter = new AbstractConverter<>() {
    @Override
    protected TicketDto convert(Ticket source) {
      TicketDto destination = new TicketDto();

      destination.setId(source.getId());
      destination.setTitle(source.getTitle());
      destination.setDescription(source.getDescription());
      destination.setReporterName(source.getReporter().getName());
      destination.setReporterSurname(source.getReporter().getSurname());
      destination.setAssigneeName(source.getAssignee().getName());
      destination.setAssigneeSurname(source.getAssignee().getSurname());
      destination.setStatus(source.getStatus().getName());
      destination.setCreatedAt(source.getCreatedAt());
      destination.setPriority(source.getPriority().getName());

      return destination;
    }
  };
}
