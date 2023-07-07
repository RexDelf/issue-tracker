package com.rexdelf.issuetrackerapp.services.impl;

import com.rexdelf.issuetrackerapp.models.Ticket;
import com.rexdelf.issuetrackerapp.repositories.TicketRepository;
import com.rexdelf.issuetrackerapp.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;

  public List<Ticket> findAll() {
    return ticketRepository.findAll();
  }
}