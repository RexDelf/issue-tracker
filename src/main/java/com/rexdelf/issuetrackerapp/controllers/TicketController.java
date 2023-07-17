package com.rexdelf.issuetrackerapp.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import com.github.fge.jsonpatch.JsonPatchException;
import com.rexdelf.issuetrackerapp.dto.JsonPatchWrapper;
import com.rexdelf.issuetrackerapp.dto.TicketDto;
import com.rexdelf.issuetrackerapp.dto.TicketPatchDto;
import com.rexdelf.issuetrackerapp.dto.TicketPostDto;
import com.rexdelf.issuetrackerapp.dto.TicketPostResponseDto;
import com.rexdelf.issuetrackerapp.dto.TicketsDto;
import com.rexdelf.issuetrackerapp.mapper.TicketMapper;
import com.rexdelf.issuetrackerapp.models.Ticket;
import com.rexdelf.issuetrackerapp.services.TicketService;
import java.io.IOException;
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

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class TicketController implements TicketsApi {

  private final TicketMapper mapper;
  private final TicketService ticketService;

  @Override
  public ResponseEntity<TicketsDto> getAll() {
    List<Ticket> tickets = ticketService.findAll();

    TicketsDto ticketsDto = new TicketsDto();

    ticketsDto.setItems(tickets.stream()
        .map(mapper::ticketToTicketDto)
        .toList());

    return new ResponseEntity<>(ticketsDto, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<TicketDto> getTicket(@PathVariable Long id) {
    Ticket ticket = ticketService.findById(id);

    return new ResponseEntity<>(mapper.ticketToTicketDto(ticket), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<TicketPostResponseDto> createTicket(@RequestBody TicketPostDto ticketPostDto) {
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

  @Override
  public ResponseEntity<TicketDto> updateTicket(@PathVariable Long id, @RequestBody TicketPatchDto patch) {

    Ticket patchedTicket = ticketService.applyPatch(patch, id);

    return new ResponseEntity<>(mapper.ticketToTicketDto(patchedTicket), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<TicketDto> jsonPatchTicket(@PathVariable Long id, @RequestBody JsonPatchWrapper patch)
      throws JsonPatchException, IOException {

    Ticket patchedTicket = ticketService.applyPatch(patch, id);

    return new ResponseEntity<>(mapper.ticketToTicketDto(patchedTicket), HttpStatus.OK);
  }
}
