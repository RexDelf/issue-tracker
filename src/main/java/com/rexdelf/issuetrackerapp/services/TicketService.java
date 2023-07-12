package com.rexdelf.issuetrackerapp.services;

import com.rexdelf.issuetrackerapp.models.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
  List<Ticket> findAll();

  Optional<Ticket> findById(Long id);

  Ticket save(Ticket ticket);
}
