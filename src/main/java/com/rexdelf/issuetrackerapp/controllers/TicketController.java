package com.rexdelf.issuetrackerapp.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import com.rexdelf.issuetrackerapp.dto.TicketDto;
import com.rexdelf.issuetrackerapp.dto.TicketPostDto;
import com.rexdelf.issuetrackerapp.dto.TicketPostResponseDto;
import com.rexdelf.issuetrackerapp.exceptions.NotFoundException;
import com.rexdelf.issuetrackerapp.mapper.TicketMapper;
import com.rexdelf.issuetrackerapp.models.Ticket;
import com.rexdelf.issuetrackerapp.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/api/tickets")
@RestController
@RequiredArgsConstructor
public class TicketController implements TicketsApi {

  private final TicketMapper mapper;
  private final TicketService ticketService;

  public ResponseEntity<List<TicketDto>> getAll() {
    List<Ticket> tickets = ticketService.findAll();

    List<TicketDto> ticketsDto = tickets.stream()
        .map(mapper::ticketToTicketDto)
        .toList();

    return new ResponseEntity<>(ticketsDto, HttpStatus.OK);
  }

  public ResponseEntity<TicketDto> getTicket(@PathVariable Long id){
    Ticket optionalTicket = ticketService.findById(id)
        .orElseThrow(() -> new NotFoundException("Entity not found for id: " + id));

    TicketDto ticketDto = mapper.ticketToTicketDto(optionalTicket);

    return new ResponseEntity<>(ticketDto, HttpStatus.OK);
  }

  public ResponseEntity<TicketPostResponseDto> createTicket(@RequestBody TicketPostDto ticketPostDto){
    Ticket savedTicket = ticketService.save(mapper.ticketPostDtoToTicket(ticketPostDto));

    String location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedTicket.getId())
        .toUriString();

    return ResponseEntity.status(CREATED)
        .header(HttpHeaders.LOCATION, location)
        .body(mapper.ticketToTicketPostResponseDto(savedTicket));
  }
}
