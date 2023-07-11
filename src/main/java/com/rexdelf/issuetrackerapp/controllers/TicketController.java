package com.rexdelf.issuetrackerapp.controllers;

import com.rexdelf.issuetrackerapp.dto.TicketDto;
import com.rexdelf.issuetrackerapp.dto.TicketPostDto;
import com.rexdelf.issuetrackerapp.mapper.TicketMapper;
import com.rexdelf.issuetrackerapp.models.Ticket;
import com.rexdelf.issuetrackerapp.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/tickets")
@RestController
@RequiredArgsConstructor
public class TicketController implements TicketsApi {

  private final TicketMapper mapper;
  private final TicketService ticketService;

  @GetMapping
  public ResponseEntity<List<TicketDto>> getAll() {
    List<Ticket> tickets = ticketService.findAll();

    List<TicketDto> ticketsDto = tickets.stream()
        .map(mapper::ticketToTicketDto)
        .toList();

    return new ResponseEntity<>(ticketsDto, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> createTicket(@RequestBody TicketPostDto ticketPostDto){
    Ticket ticket = mapper.ticketPostDtoToTicket(ticketPostDto);
    ticketService.save(ticket);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
